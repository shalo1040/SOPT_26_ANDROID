# Contents
* [HW02_회원가입 및 로그인 기능 구현하기](#HW02)
* [HW04_자동 로그인 구현하기](#HW04)

<h1 id="HW02">HW02_회원가입 및 로그인 기능 구현하기</h1>

### 로그인 화면과 회원가입 화면
<img src="/img/seminar1/login.png" alt="result view01" width="350">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="/img/seminar1/signup.png" alt="result view02" width="350"><br><br>
### HW02 Demo
<img src="/img/seminar1/hw02_demo.gif" alt="hw2_demo" width="350"><br><br>

## 액티비티에 결과값 반환하기
### startActivityForResult, putExtra, getStringExtra
다른 화면으로 전환할 때 보통 startActivity()에 호출하고자 하는 화면을 지정한 intent를 매개변수로 전달해 구현한다. 하지만 호출한 화면(B라고 부르겠다)에서 다시 이전 화면(A라고 부르겠다)으로 돌아왔을 때 어떠한 결과값을 받고 싶다면 startActivityForResult()를 호출한다.

```kotlin
val REQUEST_CODE = 1

fun signUp(view: View) {
    val intent = Intent(this, SignupActivity::class.java)
    startActivityForResult(intent, REQUEST_CODE);
}
```
<br>
이 때 B에서 A로 전달할 intent에 putExtra()를 호출해 전달하고자 하는 값을 보낼 수 있다. putExtra()의 첫 번째 매개변수에는 값을 가리키는 이름, 두 번째 매개변수에는 값을 넣어준다. 그 다음 setResult()로 전송할 값을 담은 intent를 담고 finish()를 호출할 때 이전 화면으로 돌아간다.<br><br>

```kotlin
val intent = Intent()

intent.putExtra("EMAIL", et_signUp_email.text.toString())
intent.putExtra("PASSWORD", et_signUp_passwd.text.toString())

setResult(Activity.RESULT_OK, intent)

finish()
```
<br>
이렇게 결과값을 받은 A는 onActivityResult()를 실행하는데, 이 함수를 오버라이드하여 원하는 동작을 구현한다. B에서 putExtra()로 넣은 값들은 getStringExtra()로 받아올 수 있고 이 때 매개변수에는 putExtra()에서 지정해줬던 값을 담은 이름(첫 번째 매개변수)을 적어 어떤 값을 받아올 것인지 지정해준다. 값의 자료형에 따라 getIntExtra(), getLongExtra() 등을 실행할 수 있다.<br><br>

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
위에서 설명한 과정을 그림으로 표현하면 다음과 같다.<br>
<img src="/img/seminar1/flow.png" alt="diagram"><br>

<hr>

<h1 id="HW04">HW04_자동 로그인 구현하기</h1>

### 메인 화면
<img src="/img/seminar1/login_success.png" alt="result view03" width="350"><br><br>
### HW04 Demo
<img src="/img/seminar1/hw04_demo.gif" alt="hw4_demo" width="350">
<br><br>

## 자동 로그인 구현하기
### SharedPreferences
사용자가 회원가입을 한 후 자동으로 로그인이 되어 메인화면으로 갈 수 있도록 로그인 화면으로 돌아왔을 때 실행되는 onActivityResult()에서 startActivity()로 메인 액티비티를 실행했다. 이 때, 토스트 메시지로 로그인한 사용자의 아이디와 비밀번호를 출력하도록 그 값을 넘겨주었는데, 이 때 SharedPreferences를 사용해 구현할 수 있었다. SharedPreferences를 사용하면 앱 전체에서 접근할 수 있는 하나의 Singleton 인스턴스가 생성되어 서로 다른 액티비티 사이에서 데이터를 주고받을 수 있다.<br><br>
우선 getSharedPreferences()의 첫 번째 매개변수에 데이터를 저장할 파일명을, 두 번째 매개변수에 파일의 모드를 적어준다. 모드에는 MODE_PRIVATE, MODE_WORLD_READABLE, 그리고 MODE_WORLD_WRITEABLE가 있는데, MODE_PRIVATE는 실행한 앱에서만 데이터 접근 가능하고 MODE_WORLD_READABLE은 외부 앱에서 데이터를 읽을 수 있으며 마지막으로 MODE_WORLD_WRITEABLE은 외부 앱에서 데이터를 변경시킬 수 있는 모드이다. 다시 돌아와서, getSharedPreferences()에 적절한 매개변수를 적어주면 SharedPreferences를 반환한다. <br><br>
이렇게 데이터를 저장할 수 있는 파일을 만들고 파일에 데이터를 삽입하기 위해 SharedPreferences.Editor가 필요한데, 이를 위해서는 edit()을 호출하면 된다. 이후 putString(), putBoolean(), putInt()와 같이 적절한 자료형을 삽입하는 함수를 이용해 key와 value를 짝지어주면 key에 적어준 이름으로 value가 저장된다. 마지막으로 apply() 또는 commit()를 호출해 변경된 사항을 저장해준다. apply와 commit의 차이점은 작업의 비동기/동기적 처리라는 점에 있다. apply()를 사용하면 작업의 성공 여부 출력 없이 곧바로 실행이 되며, commit()을 사용하면 작업의 성공 여부도 출력하고 실행을 하기 때문에 시간이 조금 더 걸린다고한다. 아래는 onActivityResult() 함수의 구현 코드이다.

```kotlin
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
        //로그인 입력창에 회원가입 정보 출력
        et_email.setText(data?.getStringExtra("EMAIL"))
        et_passwd.setText(data?.getStringExtra("PASSWORD"))

        //사용자가 기입한 정보 저장
        val sharedPref = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("EMAIL", data?.getStringExtra("EMAIL"))
        editor.putString("PASSWORD", data?.getStringExtra("PASSWORD"))
        editor.apply()

        //메인 페이지로 이동
        startActivity(Intent(this, MainActivity::class.java))
    } else Toast.makeText(this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
}
```
<br>
이전 코드에서 MainActivity을 실행하면 SharedPreferences로 USER_INFO 파일에 저장한 EMAIL과 PASSWORD값을 getString()으로 받아온다. 첫 번째 매개변수에는 가져오고 싶은 데이터의 key 값을, 두 번째 매개변수에는 default 값을 넣어준다. 성공적으로 데이터를 받아오면 토스트 메시지에 해당 값이 출력되는 것을 볼 수 있다.<br><br>

```kotlin
//사용자 아이디와 비밀번호 토스트 메시지로 출력
val sharedPref = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE)
val email = sharedPref.getString("EMAIL", "null")
val password = sharedPref.getString("PASSWORD", "null")
Toast.makeText(this, "아이디: ${email}\n비밀번호: ${password}", Toast.LENGTH_SHORT).show()
```
<br>
MainActivity가 실행되면 사용자가 로그인에 성공했다는 의미이므로 SharedPreferences에 LOGIN이라는 파일을 만들어 isLoginUser의 값이 true로 설정했다. 사용자가 로그아웃을 하기 전까지는 이 값이 true임을 유지할 것이므로 앱을 종료시키고 다시 실행할 때 isLoginUser의 값이 true라면 다시 로그인을 하지 않고 곧바로 메인 화면으로 전환할 수 있을 것이다.<br><br>

```kotlin
//사용자가 로그인 했음을 저장
val pref = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
val editor = pref.edit()
editor.putBoolean("isLoginUser", true)
editor.apply()
```
<br>
아래는 로그인 화면의 onCreate()에 구현한 코드이다. MainActivity에서 사용자가 이미 로그인한 유저인지 판별하는 isLoginUser의 값을 true로 설정했기 때문에 isLoginUser를 getBoolean()하면 true 값을 반환할 것이므로 곧바로 MainActivity를 실행할 것이다.<br><br>

```kotlin
//로그인 유무 판단
val pref = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
val isLoginUser = pref.getBoolean("isLoginUser", false)
if(isLoginUser) startActivity(Intent(this, MainActivity::class.java))
```        
<br><br><br>
reference: <a href="https://developer.android.com/training/basics/intents/result">다른 액티비티에서 결과 가져오기</a>, <a href="https://developer.android.com/training/data-storage/shared-preferences?hl=en#kotlin">SharedPreferences</a>