package ru.urfu.droidpractice1

data class Feedback(
    val likes: String,
    val dislikes: String,
    val likesImage: Int,
    val dislikesImage: Int,
) {

    fun doAction(action: FeedbackAction) : Feedback = getByStrings(this.likes, this.dislikes, action)



    companion object{
        fun getEmpty(): Feedback{
            return Feedback("", "", FeedbackDrawable.LikeOff.drawable, FeedbackDrawable.DislikeOff.drawable)
        }

        private fun getByIntegers(likes: Int, dislikes: Int): Feedback{
            var likesAsString = ""
            var dislikesAsString = ""
            var likeImage = FeedbackDrawable.LikeOff.drawable
            var dislikeImage = FeedbackDrawable.DislikeOff.drawable
            if (likes > 0) {
                likesAsString = likes.toString()
                likeImage = FeedbackDrawable.LikeOn.drawable
            }
            if (dislikes > 0) {
                dislikesAsString = dislikes.toString()
                dislikeImage = FeedbackDrawable.DislikeOn.drawable
            }

            return Feedback(likesAsString, dislikesAsString, likeImage, dislikeImage)
        }

        fun getByStrings(likes: String, dislikes: String, action: FeedbackAction = FeedbackAction.Nothing): Feedback{
            var likesAsInt = 0
            var dislikesAsInt = 0
            if (likes.isNotEmpty())
                likesAsInt = likes.toInt()
            if (dislikes.isNotEmpty())
                dislikesAsInt = dislikes.toInt()
            if (action == FeedbackAction.Like)
                likesAsInt += 1
            if (action == FeedbackAction.Dislike)
                dislikesAsInt += 1
            return getByIntegers(likesAsInt, dislikesAsInt)
        }
    }
}
