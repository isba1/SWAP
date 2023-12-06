package com.SwapToSustain.Server.Components;

import com.SwapToSustain.Server.DTO.UserNotification;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Model.UserPostModel;
import com.SwapToSustain.Server.Repository.UserInfoRepository;
import com.SwapToSustain.Server.Repository.UserPostRepository;
import com.google.auth.Credentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class RemovingPosts {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    UserPostRepository userPostRepository;

    private final Credentials credentials;
    private final Storage storage;
    private final String bucketName;

    @Autowired
    public RemovingPosts(Credentials credentials, Storage storage, String bucketName) throws IOException {
        this.credentials = credentials;
        this.storage = storage;
        this.bucketName = bucketName;
    }

    private void addNotificationForUnavailableOffer(UserAccountInfoModel buyer, UserAccountInfoModel seller, UserPostModel sellerPost) {
        buyer.getNotifications().add(new UserNotification(UUID.randomUUID(), "unavailable", false, seller.getUserName(), sellerPost.getPostName()));
        userInfoRepository.save(buyer);
    }


    public void removeOffersFromAllUsers(UserPostModel userPost, String userName, UserAccountInfoModel acceptedUser) {
        UserAccountInfoModel userAccount = userInfoRepository.findByUserName(userName);

        // removing from offered me and my offers account
        userAccount.getOfferedMe().remove(userPost.getPostID());

        Iterator<Map.Entry<UUID, UUID>> iterator = userAccount.getMyOffers().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, UUID> entry = iterator.next();

            // Check if the value matches the specified value and remove the entry
            if (entry.getValue().equals(userPost.getPostID())) {
                iterator.remove();
            }
        }
        userInfoRepository.save(userAccount);

        // removing from offered me and my offers in all other accounts
        for (String user: userPost.getConnectedUsers()) {
            UserAccountInfoModel foundAccount = userInfoRepository.findByUserName(user);

            // for offered me in other accounts
            for (Map.Entry<UUID, ArrayList<UUID>> entry : foundAccount.getOfferedMe().entrySet()) {
                ArrayList<UUID> arrayList = entry.getValue();

                // Check if the item exists in the ArrayList and remove it
                if (arrayList.contains(userPost.getPostID())) {
                    if (acceptedUser != null) {
                        if (foundAccount.getUserID() != acceptedUser.getUserID()) {
                            addNotificationForUnavailableOffer(foundAccount, userAccount, userPost);
                        }
                    } else {
                        addNotificationForUnavailableOffer(foundAccount, userAccount, userPost);
                    }

                    UserAccountInfoModel postFromAccount = userInfoRepository.findByUserID(userPost.getUserID());
                    boolean found = false;
                    for (Map.Entry<UUID, ArrayList<UUID>> entry2 : postFromAccount.getOfferedMe().entrySet()) {
                        ArrayList<UUID> arrayList1 = entry2.getValue();
                        if (arrayList1.contains(entry.getKey())) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        UserPostModel postModelToChange = userPostRepository.findByPostID(entry.getKey());
                        postModelToChange.getConnectedUsers().remove(postFromAccount.getUserName());
                        userPostRepository.save(postModelToChange);
                    }

                    arrayList.remove(userPost.getPostID());

                    // If the ArrayList is now empty, remove the key from the HashMap
                    if (arrayList.isEmpty()) {
                        foundAccount.getOfferedMe().remove(entry.getKey());
                    }
                }
            }

            if (foundAccount.getMyOffers().containsKey(userPost.getPostID())) {
                if (acceptedUser != null) {
                    if (!Objects.equals(foundAccount.getUserName(), acceptedUser.getUserName())) {
                        addNotificationForUnavailableOffer(foundAccount, userAccount, userPost);
                    }
                } else {
                    addNotificationForUnavailableOffer(foundAccount, userAccount, userPost);
                }
            }
            // my offers in other accounts
            UserPostModel foundPost = userPostRepository.findByPostID(foundAccount.getMyOffers().get(userPost.getPostID()));
            if (foundPost != null) {
                foundPost.getConnectedUsers().remove(userPost.getUserName());
                userPostRepository.save(foundPost);
            }

            foundAccount.getMyOffers().remove(userPost.getPostID());

            userInfoRepository.save(foundAccount);
        }

    }

    public void deleteFromGCS(UserPostModel userPostModel) {

        for (String gcsObjectName: userPostModel.getGcsObjectNames()) {
            BlobId blobId = BlobId.of(bucketName,  gcsObjectName);
            storage.delete(blobId);
        }
    }




}
