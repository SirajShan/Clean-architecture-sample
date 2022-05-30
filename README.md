
## The Starter Project

This test project uses the JSON placeholder API
to display a list of posts. The list includes the title of each post and an
excerpt from each post's body. Tapping a post opens a post details screen that
displays the title and complete body of the post.

The architecture of the application is clean architecture - MVVM (With Hilt is used for dependency injection)

## The API

The following endpoints are used to fetch data from server.

    GET https://jsonplaceholder.typicode.com/posts/

    GET https://jsonplaceholder.typicode.com/posts/{POST_ID}/

    GET https://jsonplaceholder.typicode.com/posts/{POST_ID}/comments/

##  Tasks

    The original post list and the offline post list screens should be embedded
    in a tabbed view. 
    
    The tab item for the offline post list screen should be
    badged with the number of offline posts that have been saved. The badge
    value should update in the background (i.e., without having to open the
    offline post list screen).

    Only details about the post have to be available to read offline.

## Provided resources

Included in the starter repository:

-   API response bodies so you can complete the test if you're having
    connection issues. (found under app/src/main/res/raw)
-   List of API endpoints you'll need to use for the task.
