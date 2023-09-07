package app.sample.databinding



//@BindingAdapter("load_image")
//fun loadImage(view: ImageView, url: String?) {
//    println("@@@ binding url $url")
//    view.load(url) {
//        crossfade(true)
//        placeholder(R.drawable.ic_default_user)
//        transformations(CircleCropTransformation())
//        diskCachePolicy(CachePolicy.DISABLED)
////        memoryCachePolicy(CachePolicy.DISABLED)
//    }
//}

//@BindingAdapter("load_image_or_video")
//fun loadImageOrVideo(view: View, news: News) {
//    if (news.advertisement?.file_type == "image") {
//        if (view is ImageView) {
//            view.load(news.advertisement.image_video) {
//                crossfade(true)
//                placeholder(R.drawable.ic_default_user)
//                diskCachePolicy(CachePolicy.DISABLED)
////        memoryCachePolicy(CachePolicy.DISABLED)
//            }
//        }
//    } else {
//        if (view is VideoView) {
//
//
//            if (news.advertisement?.image_video != null) {
//                view.setVideoURI(Uri.parse(news.advertisement.image_video))
//                view.requestFocus()
//                view.start()
//            }
//        }
//    }
//}
//
//
//@BindingAdapter("like_count")
//fun showLikeCount(view: AppCompatTextView, totalLike: String) {
//    when {
//        totalLike.toInt() > 1 -> {
//            view.text = "$totalLike " + AppUtil.string(R.string.likes)
//        }
//        totalLike.toInt() == 1 -> {
//            view.text = "$totalLike " + AppUtil.string(R.string.like)
//        }
//        else -> view.visibility = View.GONE
//
//    }
//}
//
//@BindingAdapter("comment_count")
//fun showCommentCount(view: AppCompatTextView, totalComments: String) {
//    when {
//        totalComments.toInt() > 1 -> {
//            view.text = "$totalComments " + AppUtil.string(R.string.comments)
//        }
//        totalComments.toInt() == 1 -> {
//            view.text = "$totalComments " + AppUtil.string(R.string.comment)
//        }
//        else -> view.visibility = View.GONE
//
//    }
//}