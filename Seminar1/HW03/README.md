<h1>HW03_ConstraintLayout, RelativeLayout, LinearLayout로<br> 로그인 화면 만들기</h1>

### 로그인 화면
<img href="/img/seminar1/login.png" alt="login" width="350">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img href="/img/seminar1/wireframe.PNG" alt="wireframe" width="350"><br><br>

## ConstraintLayout
이 레이아웃에서 UI를 배치할 때 상하좌우의 constraint를 설정해 그 위치에 제약을 주는 방식으로 만든다. 가로로 중앙에 배치하고 싶다면 좌우의 constraintStart과 constraintEnd를 화면의 양 끝으로 맞추면 되고 반대로 세로로 중앙에 배치하고 싶다면 위아래의 constraintTop과 constraintBottom을 화면 위 아래 끝으로 맞춰주면 된다.

```kotlin
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintTop_toTopOf="parent"
```
<br>

## RelativeLayout
모든 구성 UI에 id를 부여해 상대적인 위치를 알려줌으로써 UI를 배치할 수 있다. 가로로 중앙에 배치하고 싶다면 android:layout_centerHorizontal="true"을 적는다.

```kotlin
<ImageView
    android:id="@+id/imageView"     //id
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_centerHorizontal="true"	//가운데 배치
    android:src="@drawable/sopt_logo" />

<EditText
    android:id="@+id/et_email"      //id
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_below="@+id/imageView"	//id가 imageView인 요소의 아래에 위치시킨다.
    android:inputType="textEmailAddress" />
```
<br>

## LinearLayout
요소들을 넣어주는 순서대로 레이아웃에 배치된다. 가로로 배치될 때는 orientation이 horizontal, 세로일 때는 vertical이다. 가로로 중앙에 배치하고 싶을 때는 android:layout_gravity="center"을 적는다.

```kotlin
<LinearLayout
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:orientation="horizontal"		//넣어주는 순서대로 가로로 배치
    android:layout_gravity="center"     //가운데 배치
    android:layout_marginTop="32dp">

	<TextView
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:text="아직 회원이 아니신가요?"/>
</LinearLayout>
```