package app.sample.utils

object ValidationUtils {
    fun isValidEmail(email: String?): Boolean {
        email?.let {
            val passwordPattern = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}"
            val passwordMatcher = Regex(passwordPattern)

            return passwordMatcher.find(it) != null
        } ?: return false
    }

    fun isValidPhone(phone: String?): Boolean {
        phone?.let {
            val phonePattern = "\\d{3}[- .]?\\d{3}[- .]?\\d{4}\$"
            val phoneMatcher = Regex(phonePattern)
            return phoneMatcher.find(it) != null
        } ?: return false
    }

    fun isValidPassword(pw: String?): Boolean {
        pw?.let {
            //val pwPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&;+=])(?=\\\\S+\$).{6,50}\$"
            val pwPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,50}$"
            val pwMatcher = Regex(pwPattern)
            return pwMatcher.find(it) != null
        } ?: return false
    }

    fun isValidConfirmPassword(pw: String?, newpw:String?): Boolean {
        pw?.let {
            return if (newpw?.isNotEmpty()!!){
                newpw==pw
            }else
                false
        } ?: return false
    }
}