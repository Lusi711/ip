# User Guide

## Table of Contents

[1. Introduction](#1-introduction) <br>
[2. Quick start](#2-quick-start) <br>
[3. Features](#3-features) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.1. Adding a todo](#31-adding-todos---todo) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.2. Adding a deadline](#32-adding-deadlines-deadline) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.3. Adding an event](#33-adding-events-event) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.4. Listing tasks](#34-listing-all-tasks-list) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.5. Marking a task as done](#35-marking-a-task-as-done-done) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.6. Finding tasks by keyword](#36-finding-tasks-by-keyword-find) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.7. Viewing tasks on a date](#37-viewing-tasks-on-a-date-view) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.8. Deleting a task](#38-deleting-a-task-delete) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.9. Exiting the program](#39-exiting-the-program-bye) <br>
[4. Command summary](#4-command-summary) <br>
[5. FAQ](#5-faq) <br>
[6. Troubleshooting](#6-troubleshooting)

# 1. Introduction

Welcome to the *Duke User Guide*! Duke is a desktop app for managing tasks,deadlines and events, optimized for use via a Command Line Interface (CLI). <br>
This user guide gives you detailed instructions on the Duke installation process and available features.

# 2. Quick Start
1. Ensure that you have Java `11` or above installed in your Computer.
2. Download the latest `ip.jar` from [here](https://github.com/Lusi711/ip/releases/tag/v2%2C0).
3. Copy the file to the folder you want to use as the *home folder* for your Duke.
4. Open a command window in that folder.
5. Run the command `java -jar ip,jar` to start the app. 
6. If the setup is correct, you should see a greeting message as follows:
```
------------------------------------------------------------------
    Hello from
     ____        _        
    |  _ \ _   _| | _____
    | | | | | | | |/ / _ \
    | |_| | |_| |   <  __/
    |____/ \__,_|_|\_\\___
    Hello! I'm Duke.
    What can I do for you?
------------------------------------------------------------------
```
7. Type the command below the separation line and press Enter to execute it.
8. Refer to the [Features](#3-features) below for details of each command.

# 3. Features 
Notes about command format:
* Words in `<UPPER-CASE>` are the parameters supplied by the user. 
e.g. in `todo <DESCRIPTION>`, `DESCRIPTION` is a parameter.

## 3.1. Adding ToDos - `todo`
Adds a task without any date/time attached to it. <br>
Format: `todo <DESCRIPTION>`
* The task is assumed to be not done.
>Example
```
>>> todo read book
------------------------------------------------------------------
    Got it. I've added this task:
        [T][✘] read book
    Now you have 1 task in the list.
------------------------------------------------------------------
```

## 3.2. Adding deadlines: `deadline`
Adds a task that need to be done before a specific date and time. <br>
Format: `deadline <DESCRIPTION> \by <DATE> <TIME>`
* Enter `DATE` in the format: `YYYY-MM-DD`.
* Enter `TIME` in the 24-hour format: `HHmm` e.g. 11:59 pm is expressed as `2359`.

>Example
```
>>> deadline return book /by 2020-06-06 2359
------------------------------------------------------------------
    Got it. I've added this task:
        [D][✘] return book (by: Jun 6 2020, 11:59 PM)
    Now you have 2 tasks in the list.
------------------------------------------------------------------
```

## 3.3. Adding events: `event`
Adds a task that starts at a specific time. <br>
Format: `event <DESCRIPTION> \by <DATE> <START_TIME>`
* The `DATE` must be in the format: `YYYY-MM-DD'.
* The `START_TIME` must be in the 24-hour format: `HHmm` e.g. 11:59 pm is expressed as `2359`.
>Example
```
>>> event project meeting /at 2020-08-06 1400
------------------------------------------------------------------
    Got it. I've added this task:
        [E][✘] project meeting (at: Aug 6 2020, 02:00 PM)
    Now you have 3 tasks in the list.
------------------------------------------------------------------
```

## 3.4. Listing all tasks: `list`
Shows a list of all the tasks in task list in order of input. <br>
Format: `list`
>Example
```
>>> list
------------------------------------------------------------------
    Here are the tasks in your list:
    1. [T][✘] read book
    2. [D][✘] return book (by: Jun 6 2020, 11:59 PM)
    3. [E][✘] project meeting (at: Aug 6 2020, 02:00 PM)
------------------------------------------------------------------
```

## 3.5. Marking a task as done: `done`
Marks a specified task as done. <br>
Format: `done <INDEX>`
* Marks the task in the specified `INDEX` as done. 
* The index refers to the index number shown in the task list.
* The index **must be a positive integer** 1,2,3...
> Example
```
>>> done 1
------------------------------------------------------------------
    Nice! I've marked this task as done:
      [T][✓] read book
------------------------------------------------------------------
```

## 3.6. Finding tasks by keyword: `find`
Find all tasks with description that matches the given keyword. <br>
Format: `find <KEYWORD>`
* Searches for tasks with description that matches the specified `KEYWORD`.
* The search is case-insensitive e.g. `book` will match `Book`.
* The search matches only the description.
* The search matches only full words e.g. `book` will not match `books`.
> Example
```
>>> find book
------------------------------------------------------------------
    Here are the matching tasks in your list:
    1. [T][✓] read book
    2. [D][✘] return book (by: Jun 6 2020, 11:59 PM)
------------------------------------------------------------------
```

## 3.7. Viewing tasks on a date: `view`
Find deadlines/events that are due/occur on the given date.
Format: `view <DATE>`
* Searches for tasks with deadline/start time which matches the specified `DATE`.
* `DATE` must be in the format : `YYYY-MM-DD` e.g. `2020-09-28` means 28th September 2020.
> Example
```
>>> view 2020-08-06
------------------------------------------------------------------
    Here are the matching tasks in your list:
    1. [E][✘] project meeting (at: Aug 6 2020, 02:00 PM)
------------------------------------------------------------------
```

## 3.8. Deleting a task: `delete`
Deletes an **existing** task from the list.
Format: `delete INDEX`
* Deletes the task in the specified `INDEX`. 
* The index refers to the index number shown in the complete task list.
* The index **must be a positive integer** 1,2,3...
> Example
```
>>> delete 3
------------------------------------------------------------------
    Noted. I've removed this task:
        [E][✘] project meeting (at: Aug 6 2020, 02:00 PM)
    Now you have 2 tasks in the list.
------------------------------------------------------------------
```

## 3.9. Exiting the program: `bye`
Exits the program and saves the task list data automatically in the hard disk. <br>
Format: `bye`
* If you exit the program successfully, you should see a farewell message as follows:
```
------------------------------------------------------------------
        Bye. Hope to see you again soon!
------------------------------------------------------------------
```

# 4. Command Summary
Actions | Format, Examples
------- | ----------------
Add Todo| `todo DESCRIPTION`
Add deadline | `deadline DESCRIPTION \by DATE TIME`
Add event | `event DESCRIPTION \by DATE TIME`
List | `list`
Mark as done | `done INDEX`
Find by keyword | `find KEYWORD`
Find by date | `view DATE`
Delete | `delete`
Exit | `bye`

# 5. FAQ
Q. The status icons of my tasks are not showing in the terminal. e.g. 
```
[T][?] read book
```
A. The console of your Computer does not support the Code Page. 
1. Type `chcp 65001` to change the Code Page of the command line.
2. Type `java -Dfile.encoding=UTF-8 -jar ip.jar` whenever you start the application.

# 6. Troubleshooting
The table below shows the possible error messages you may see, the possible reasons and ways to deal with them. <br>
Message | Possible reason | What to do now
------- | --------------- | --------------
```OOPS!!! I'm sorry, but I don't know what that means :-(``` | You typed a command that does not exist in features. | Refer to [Features](#3-features) to learn the proper formatting.
```OOPS!!! Please enter a valid description.``` | You may have missed typing the task description or index. | Refer to [Features](#3-features) to learn the proper formatting.
```OOPS!!! Please enter a valid index``` | The task index you entered does not exist on the task list. | Reenter the command with a valid index.
```OOPS!!! There are no tasks in the list to execute command: ``` | The task list is empty. |
```OOPS!!! Please provide a specific time after "``` | There is a missing date/time when adding a deadline/event. | Refer to [Features](#3-features) to learn the proper formatting.
```OOPS!!! Please input the date and time in the correct format(YYYY-MM-DD HHmm): ``` | The date/time input does not follow the required format strictly. | Refer to [Features](#3-features) to learn the proper formatting.