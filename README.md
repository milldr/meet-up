# Meet-Up
#### the scheduling assistant

------
## Authors
#### Daniel Miller
#### Jeff Liao
#### Kyle Williams
#### CSE 5236 - Mobile Apps

------
## Part 5

### Installing

1. Clone the repository
2. Open it in Android Studio
3. Build the app
4. Run on your chosen emulator (or physical device).


### Non-functional Use Cases
#### Accounts:

+ Account creation - User can create an account.
+ Account login - User can log into an account.
+ Account logout - User can log out of an account.
 
#### Groups:

+ Group creation - User can create a group.
+ Add member - User can add a member to a group at creation

#### Meetups:

+ Meetup creation - User can create a meetup.
+ Meetup joining - User can join a meetup.
+ Meetup leaving - User can leave a meetup.

### Profiler Performance Upgrade
## Memory Use Improvement

Previously, the user to meetup association was accomplished by a list of user keys in stored in each meetup. Now that has been replaced by a relational table. You can see in the screenshots that this has decreased the memory used (for 3 meetups) from around 8 MB to around 3 MB - a significant improvement!
### Before
![Before](/screenshots/before.png)
### After
![After](/screenshots/after.png)


### Screenshots

## Login and Sign Up
![Create](/screenshots/createaccount.png)
![Login](/screenshots/login.png)

## Home Screen and Navigation
![Main](/screenshots/main.png)
![Navigation](/screenshots/nav.png)
![Overflow](/screenshots/overflow.png)

## Calendar
![Calendar](/screenshots/cal1.png)
![Calendar 2](/screenshots/cal2.png)

## Adding a Meetup - Appear in main page
![Meetup](/screenshots/meetup.png)
![Main Page w/ MU](/screenshots/showmeetup.png)

## Groups
![Groups](/screenshots/group.png)
![Add Group](/screenshots/addgroup.png)

## Settings - Logout
![Logout](/screenshots/logout.png)


------
