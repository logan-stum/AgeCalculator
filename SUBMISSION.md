# Practice I — Age Calculator App
### Submission Write-Up

---

## App Description

The Age Calculator App is a simple Android application that allows a user to enter their first name, last name, and date of birth (month, day, and year in separate fields), then tap a button to see their age displayed in a Toast message. The app validates all inputs before calculating and shows helpful error messages if something is missing or out of range.

---

## Screenshot

> **Note to instructor:** A screenshot of the app running on the Android Emulator is attached separately as `screenshot.png`. The screenshot shows the app with sample data filled in (First Name: Jane, Last Name: Doe, Month: 07, Day: 04, Year: 1990) and the resulting Toast message reading: *"Hello, Jane Doe! You are 34 years old."*

---

## Challenges Faced

**1. Understanding how the layout file connects to the Java file**
One of the first things that tripped me up was understanding that `activity_main.xml` and `MainActivity.java` are two completely separate files that have to be linked together manually. You set up the visual layout in XML, but then you have to use `findViewById()` in Java to actually grab each element and make it do something. Once I understood that pattern, the rest started to click.

**2. Splitting the date into three separate fields**
My first instinct was to have the user type the full date into one box (like `07/04/1990`), which is what the original SimpleDateFormat approach does. But parsing a formatted date string added a lot of complexity. Breaking it into three number fields (Month, Day, Year) made the validation logic much simpler and easier to understand — I just check if each number is in a valid range.

**3. The Calendar month being off by one**
When I first tested the birthday-check logic, ages kept coming out wrong by one year for certain months. I eventually figured out that Java's `Calendar.MONTH` returns 0 for January, 1 for February, and so on — so you have to add 1 to it to get the "real" month number. This is a well-known Java quirk but it's not obvious at all when you first run into it. I fixed it with `Calendar.getInstance().get(Calendar.MONTH) + 1`.

**4. Getting the Toast to show the right message**
I initially had the Toast inside the validation checks by mistake, which meant it would sometimes fire when it shouldn't, or show a blank name. Moving all validation to the top of the method (with early `return` statements) and only building the Toast message at the very end fixed this.

---

## Suggestions for Improving the App

**1. Add a Date Picker dialog**
Right now the user has to type the month, day, and year manually into three boxes. Android has a built-in `DatePickerDialog` that lets users tap through a calendar to pick their birthday. This would make the app much harder to use incorrectly since it eliminates typos and invalid dates entirely.

**2. Display the result on the screen instead of (or in addition to) a Toast**
Toast messages disappear after a few seconds. A better experience would be to show the calculated age in a `TextView` directly on the screen so the user can still see it after the Toast fades. For example, a large label at the bottom that says *"You are 34 years old"* would be more readable and permanent.

**3. Validate impossible dates more carefully**
The current validation checks that the month is 1–12 and the day is 1–31, but it doesn't catch things like February 31st or April 31st. A future version could check the actual number of days in the given month (accounting for leap years) to reject dates that don't exist on the calendar.

**4. Add a "Clear" or "Reset" button**
It would be convenient to have a button that wipes all the input fields at once so the user doesn't have to delete each field manually before entering a new person's information.

**5. Support for calculating the age in months or days**
An optional enhancement would be to also display how many months old the user is, or even a fun "you've been alive for X days" message. This would make the app more engaging without requiring a major redesign.

---

*Submitted for CS Graduate Mobile Development — Practice Assignment I*
