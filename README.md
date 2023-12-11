# Swap Wear Apparel Platform (SWAP)

## Features

1. **User Authentication and Profiles:**
   - User registration and login.
   - User profiles with details like username, contact information, interests.

2. **Clothing Posting and Trading:**
   - Ability for users to post clothes they want to trade.
   - Include details such as item description, size, photo of item.
   - Users can make offers for posts with their posts.

3. **Recommended Feed:**
   - Generates a personalized feed based on user interests and following.
  
4. **Explore Categories:**
   - Browse and explore different categories of clothing (e.g., tops, bottoms, shoes).
   - Filtered views for specific types of clothing.

5. **Filtered Searches:**
   - Search functionality with filters for specific users.
   - Filters based on categories, sizes, brands, etc.

6. **Follow and Feed:**
   - Users can follow other users.
   - Personalized feed displaying posts from followed users.

7. **Trade Offers:**
   - Users can make trade offers on items they are interested in.
   - Users can view what other users are offering them
   - Users can accept of decline trade offers.
    
8. **Offer Status Notifications:**
   - Real-time notifications for the status of trade offers (accepted, declined, or item no longer available).


## Getting Started Locally

### Prerequisites

- [Git](https://git-scm.com/)
- [Maven](https://maven.apache.org/install.html)
- [Node.js](https://nodejs.org/) and [npm](https://www.npmjs.com/)
- [Java](https://www.oracle.com/java/technologies/)

### Local Deployment 
1. **Clone Repository**

2. **Navigate to Server Directory:**
   - `cd SWAP/Server`

3. **Build Server Code:**
   - `mvn clean install -DskipTests`

4. **Run the Server Code on localhost (port 8080):**
   - `java -jar target/Server-0.0.1-SNAPSHOT.jar`

5. **Navigate to Client SRC Directory:**
   - `cd SWAP/client`

6. **Install Node Packages**
   - `npm install`

7. **Run Client Code on localhost (port 3000):**
   - `npm run dev` or `npm start`
  
### Release 1.0 Updates - (December 10th, 2023)
1. Fixed bugs relating to overlapping posts
2. Changed default page to Login page
3. Changed to Justin's database
4. Update prerequisites to running web application locally
5. Update instructions to how to run SWAP web application
