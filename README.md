# Amazon DynamoDB - JAVA - Retrieving data
A basic example that shows how to scan and query tables data from Dynamodb in an Android Application.

## **Showcase**
![main_framed](https://github.com/betianaminio/android_dynamodb_retrieve_data/blob/master/imgs/main_framed.png)
![main_2_framed](https://github.com/betianaminio/android_dynamodb_retrieve_data/blob/master/imgs/main_2_framed.png)
![track_list_framed](https://github.com/betianaminio/android_dynamodb_retrieve_data/blob/master/imgs/track_list_framed.png)
![track_list_2_framed](https://github.com/betianaminio/android_dynamodb_retrieve_data/blob/master/imgs/track_list_2_framed.png)

## **Prerequisites**

* An Amazon AWS Account
* Android Studio
* Glide or any other image loader

## **Project configuration**

To be able to use Amazon dynamodb features, you have to add the dependencies into the app gradle file. So, open your app build.gradle file and add the following into the dependencies section:

        dependencies {
                  .....
                compile 'com.amazonaws:aws-android-sdk-core:2.3.6'
                compile 'com.amazonaws:aws-android-sdk-s3:2.3.6'
                compile 'com.amazonaws:aws-android-sdk-ddb:2.3.6'
                compile 'com.amazonaws:aws-android-sdk-ddb-mapper:2.3.6'
                  .....
         }

You'll need to add Glide if you want to download an image from an url specified. To do that, add the following dependencies into the file mentioned above:

        compile 'com.github.bumptech.glide:glide:3.7.0'
        compile 'com.android.support:support-v4:25.0.1'


## **Useful links**

* http://docs.aws.amazon.com/mobile/sdkforandroid/developerguide/setup.html
* https://github.com/bumptech/glide/releases
