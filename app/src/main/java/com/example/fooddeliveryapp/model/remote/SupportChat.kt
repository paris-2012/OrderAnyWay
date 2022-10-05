package com.example.fooddeliveryapp.model.remote

data class SupportChat(var viewType: Int = 1, var text: String = " ")

data class SupportChatSenderData(
    var button1: String = "hello world 1",
    var button2: String = "hello world 2",
    var button3: String = "hello world 3"
)
