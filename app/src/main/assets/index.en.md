Simpletask Revived
==================
Ver en [Español](./index.es.md), auf [Deutsch](./index.de.md) 

[Simpletask Revived](https://github.com/scaprisecca/simpletask-android) is based on the brilliant [todo.txt](http://todotxt.com) by [Gina Trapani](http://ginatrapani.org/). The goal of the application is to provide a tool to ['Getting Things Done'](https://gettingthingsdone.com/) (GTD) without providing an overwhelming amount of options. Even though Simpletask Revived can be customised by a fairly large amount of settings, the defaults should be sane and require no change.

[Simpletask Revived](https://github.com/scaprisecca/simpletask-android) can be used as a very simple todo list manager or as a more complex action manager for GTD or [Manage Your Now](./MYN.en.md).

Extensions
----------

Simpletask Revived supports the following todo.txt extensions:

-   Due date as `due:YYYY-MM-DD`
-   Start/threshold date as `t:YYYY-MM-DD`
-   Recurrence with `rec:\+?[0-9]+[dwmyb]` as described [here](https://github.com/bram85/topydo/wiki/Recurrence) but with a twist.
    - By default Simpletask Revived will use the date of completion for recurring as described in the link. However if the rec includes a plus (e.g. `rec:+2w`), the date is determined from the original due or threshold date..
    - `rec:1b` will recur after 1 weekday (mnemonic *b*usiness-day). 
    - The format is described by a regular expression, so in words the syntax is `rec:` followed by an optional `+` then 1 or more numbers and then followed by one of `d`ay, `w`eek, `m`onth or `y`ear. For example `rec:12d` sets up a 12 day recurring task.
- Hidden tasks with the specified tag `h:1`, this allows dummy tasks with predefined lists and tags so that lists and tags will be available even if the last task with the tag/list is removed from `todo.txt`. These tasks will not be shown by default. You can temporarily display them from the Settings.

Support
-------

Simpletask Revived currently ships with the existing translation set from the original project. Translation workflow refresh is planned separately.

If you want to log an issue or feature request for [Simpletask Revived](https://github.com/scaprisecca/simpletask-android/) you can go to [the tracker](https://github.com/scaprisecca/simpletask-android/issues).


Community
---------

Join the chat on IRC at [#simpletask on Libera Chat](https://web.libera.chat), or Matrix at [#simpletask:matrix.org](https://matrix.to/#/#simpletask:matrix.org).


Check the menu for more help sections or click below.

- [User Interface](./ui.en.md) Help on the user interface.
- [Changelog](./changelog.en.md)
- [Lists and Tags](./listsandtags.en.md) Why does Simpletask Revived use lists and Tags instead of the Contexts and Projects from todo.txt?
- [Defined intents](./intents.en.md) Intents that can be used for automating Simpletask Revived
- Using Simpletask Revived for 'The One Minute To-Do List' or 'Master Your Now' [1MTD/MYN](./MYN.en.md)
- [Using Lua](./script.en.md)
