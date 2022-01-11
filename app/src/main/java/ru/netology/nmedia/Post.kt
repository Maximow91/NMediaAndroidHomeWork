package ru.netology.nmedia

data class Post(val id:Long,
                val author:String,
                val content:String,
                val published:String,
                val likesValue:Int,
                val sharedValue:Int,
                val likedByMe:Boolean)

