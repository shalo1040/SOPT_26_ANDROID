# Contents
* [Demo](#Demo)
* [Structure](#Structure)
* RequestSignup, ResponseSignup
* RequestToServer
* SignupActivity

* [확장함수](#확장함수)
	* [showToast](#showToast)
	* [textChangedListener](#textChangedListener)
	* [customEnqueue](#customEnqueue)

# Retrofit2 활용해 서버와 통신하기(로그인, 회원가입)
### Demo
<img src="/img/seminar3/server.gif" alt="hw01 demo" width="350"><br><br>

### Structure
![01](/img/seminar3/structure.png)

<br>[목차로 돌아가기](#Contents)<br><br>
<hr><br><br>

# 확장함수

### showToast
```kotlin
fun Context.showToast(msg : String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
```
### textChangedListener
```kotlin
fun EditText.textChangedListener(textChanged : (CharSequence?) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) = Unit

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            textChanged(s)
        }
    })
}
```
### customEnqueue
```kotlin
fun<ResponseType> Call<ResponseType>.customEnqueue(
    onFail : () -> Unit,
    onError : () -> Unit,
    onSuccess : (ResponseType) -> Unit
) {
    this.enqueue(object : Callback<ResponseType> {
        override fun onFailure(call: Call<ResponseType>, t: Throwable) {
            onFail()
        }

        override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
            response.body()?.let {
                onSuccess(it)
            } ?: onError()
        }

    })
}
```

<br>[목차로 돌아가기](#Contents)<br><br>

reference: [Retrofit2 API](https://square.github.io/retrofit/2.x/retrofit/retrofit2/http/package-summary.html), [Retrofit2 사용법](https://medium.com/mindorks/understand-how-does-retrofit-work-c9e264131f4a)