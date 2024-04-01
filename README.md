Basic Banking System

First we create a user using **/user/create** end-point
Later we create an account, mapped to a particular user using **many to one** relation.

All this information is stored in **H2 in-memory database**

We can fetch all the accounts related to one particular user using **user/accounts** end-point

We can perform deposit, withdraw, and transfer operations for a particular account with proper **exception handling**
