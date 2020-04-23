# HW01_ConstraintLayout 심화 학습
<br><br>
## 결과화면
![result view](/img/seminar1/HW01.png)
<br><br>
## ConstraintDimentionRatio
width와 height 값을 직접 조절하지 않고 원하는 비율로 버튼, 이미지뷰 등을 설정할 수 있다. 바로 constraintDimentionRatio에 원하는 비율을 width:height 형태로 입력하면 되는데, 이 때 width 또는 height의 값은 0dp를 주어야한다. 길이 값이 0dp인 의미는 가능한 제한 영역 중에서 가장 넓은 값을 나타내는 match_constraint와 같다. 나는 width에 margin을 주어 원하는 길이로 조절했기에 height 값에 0dp를 입력했고 ImageView를 1:1로 설정하고 싶었기에 비율은 1:1을 입력했다. 해당 코드는 아래와 같다.<br>
```kotlin
android:layout_width="match_parent"
android:layout_height="0dp"
app:layout_constraintDimentionRatio="1:1"
```
<br><br>
## Chains
음료의 이름과 가격을 나타내는 두 개의 TextView를 가로로 나란히 배치하기 위해 이들을 Chain으로 묶어주었다. chainStyle은 화면에 이들이 보여지는 여러 방식을 제공하는데, 나는 두 텍스트를 각각 화면 끝에 배치하고 싶어 다음고 같이 spread_inside 값을 주었다.<br>
```kotlin
app:layout_constraintHorizontal_chainStyle="spread_inside"
```
<br><br>
## Guideline
Constraint 레이아웃에는 helper 객체가 존재한다. Helper 클래스에는 Barrier, Group, 그리고 Guideline 클래스가 존재하고 이를 이용해 화면 레이아웃을 만들 수 있다. 음료에 대한 설명을 담은 긴 글이 화면 끝까지 닿게하지 않기 위해 Guideline을 사용해 TextView의 width에 제한을 둘 수 있었다. 먼저 원하는 마우스 우클릭을 통해 Guideline을 생성한 후 화면의 오른쪽 끝에서 75dp 길이 만큼의 범위에 내에서 글이 써질 수 없도록 다음과 같이 코드를 입력했다.<br>
```kotlin
android:id="@+id/guideline"
android:orientation="vertical"
app:layout_constraintGuide_end="75dp"
```
<br>그 다음 TextView의 width에 0dp를 입력해 match_constraint가 되도록했고, 오른쪽 constraints 값에 다음과 같이 guideline을 입력해 원하는 글의 범위를 제한했다.<br>
```kotlin
android:layout_width="0dp"
android:layout_height="wrap_content"
app:layout_constraintEnd_toStartOf="@+id/guideline"
```
<br><br>
## textStyle
음료의 이름을 굵게 표시하기 위해 textStyle 속성에 bold 값을 입력했다. 또 다른 입력 값으로는 normal, italic이 있다.
<br><br><br>
reference: [ConstraintLayout](https://developer.android.com/reference/android/support/constraint/ConstraintLayout#DimensionConstraints), [Guideline](https://developer.android.com/reference/android/support/constraint/Guideline)