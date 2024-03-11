package com.example.recyclerviewinanutshell

object Constants {

    fun getUserList(): ArrayList<User> {
        var userList = ArrayList<User>()

        val usersItem = listOf<User>(
            User("Rifat", "rifat@tikweb.com", R.drawable.baseline_email),
            User("Ihan", "ihan@tikweb.com", R.drawable.baseline_email),
            User("Arisha", "arisha@tikweb.com", R.drawable.baseline_email),
            User("Riyan", "raiyan@tikweb.com", R.drawable.baseline_email),
            User("Ramisha", "ramisha@tikweb.com", R.drawable.baseline_email),
            User("Farhan", "farhan@tikweb.com", R.drawable.baseline_email),
            User("Ahnaf", "ahnaf@tikweb.com", R.drawable.baseline_email),
        )

        userList.addAll(usersItem)

        return userList
    }

}