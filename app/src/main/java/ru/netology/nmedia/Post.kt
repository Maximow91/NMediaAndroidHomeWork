package ru.netology.nmedia

data class Post(val id:Long,
                val author:String,
                val content:String,
                val published:String,
                var likesValue:Int=0,
                var sharedValue:Int=0,
                var likedByMe:Boolean = false)

