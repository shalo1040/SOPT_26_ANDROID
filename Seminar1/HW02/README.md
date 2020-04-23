# HW02_회원가입 및 로그인 기능 구현하기
<br><br>
## 로그인 화면과 회원가입 화면
![result view01](/img/seminar1/login.png)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
![result view02](/img/seminar1/signup.png)<br>
## Demo
![demo](/img/seminar1/hw02_demo.gif)
<br><br>
## 액티비티에 결과값 반환하기
### startActivityForResult, putExtra, getStringExtra
다른 화면으로 전환할 때 보통 startActivity()에 호출하고자 하는 화면을 지정한 intent를 매개변수로 전달해 구현한다. 하지만 호출한 화면(B라고 부르겠다)에서 다시 이전 화면(A라고 부르겠다)으로 돌아왔을 때 어떠한 결과값을 받고 싶다면 startActivityForResult()를 호출한다. 
<br>
```kotlin
val REQUEST_CODE = 1

fun signUp(view: View) {
    val intent = Intent(this, SignupActivity::class.java)
    startActivityForResult(intent, REQUEST_CODE);
}
```
<br>
이 때 B에서 A로 전달할 intent에 putExtra()를 호출해 전달하고자 하는 값을 보낼 수 있다. putExtra()의 첫 번째 매개변수에는 값을 가리키는 이름, 두 번째 매개변수에는 값을 넣어준다. 그 다음 setResult()로 전송할 값을 담은 intent를 담고 finish()를 호출할 때 이전 화면으로 돌아간다. 
<br>
```kotlin
val intent = Intent()

intent.putExtra("EMAIL", et_signUp_email.text.toString())
intent.putExtra("PASSWORD", et_signUp_passwd.text.toString())

setResult(Activity.RESULT_OK, intent)

finish()
```
<br>
이렇게 결과값을 받은 A는 onActivityResult()를 실행하는데, 이 함수를 오버라이드하여 원하는 동작을 구현한다. B에서 putExtra()로 넣은 값들은 getStringExtra()로 받아올 수 있고 이 때 매개변수에는 putExtra()에서 지정해줬던 값을 담은 이름(첫 번째 매개변수)을 적어 어떤 값을 받아올 것인지 지정해준다. 값의 자료형에 따라 getIntExtra(), getLongExtra() 등을 실행할 수 있다.
<br>
```kotlin
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
        et_email.setText(data?.getStringExtra("EMAIL"))
        et_passwd.setText(data?.getStringExtra("PASSWORD"))
    } 
    else Toast.makeText(this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
}
```
<br>
위에서 설명한 과정을 그림으로 표현하면 다음과 같다.
![diagram](/img/seminar1/startactivityforresult_flow.png)
<br><br><br>
reference: [다른 액티비티에서 결과 가져오기](https://developer.android.com/training/basics/intents/result)