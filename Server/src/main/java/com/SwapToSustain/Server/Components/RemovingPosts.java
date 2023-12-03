package com.SwapToSustain.Server.Components;

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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

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

    public void removeOffersFromAllUsers(UserPostModel userPost, String userName) {
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
                    arrayList.remove(userPost.getPostID());

                    // If the ArrayList is now empty, remove the key from the HashMap
                    if (arrayList.isEmpty()) {
                        foundAccount.getOfferedMe().remove(entry.getKey());
                    }
                }
            }

            // my offers in other accounts
            foundAccount.getMyOffers().remove(userPost.getPostID());

            userInfoRepository.save(foundAccount);
        }

    }

    public void deleteFromGCS(UserPostModel userPostModel) {
        final String folderName = "Post_Images";

        for (String gcsObjectName: userPostModel.getGcsObjectNames()) {

            BlobId blobId = BlobId.of(bucketName, folderName + "/" + gcsObjectName);
            storage.delete(blobId);
        }
    }

}
