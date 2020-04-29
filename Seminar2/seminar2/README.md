# Contents
* [HW01_BottomNavigationView, ViewPager, RecyclerView 실습](#HW01)
	* [BottomNavigationView](#BottomNavigationView)
	* [ViewPager](#ViewPager)
	* [RecyclerView](#RecyclerView)

## HW01
### BottomNavigationView
화면 아래에 아이콘을 두어 화면을 전환할 수 있는 bottom navigation을 사용하기 위해 관련된 기능을 제공하는 라이브러리를 build.gradle에 추가했다. (자세한 정보는 [여기](https://material.io/develop/android/components/bottom-navigation-view/)에) 네비게이션에 원하는 아이콘을 넣기 위해서는 먼저 menu 요소에 대한 정보를 지정해줄 xml을 생성하고 \<menu> 태그 아래의 \<item> 태그에 다음과 같이 하나의 아이템에 대한 아이디, <u title="Vector Asset을 생성해 구글에서 기본으로 제공하는 아이콘의 xml을 사용할 수 있다.">*아이템 디자인 정보*</u>, 그리고 아이콘 밑에 보여줄 탭의 이름을 적어준다.
```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:id="@+id/menu_home"		//아이디 지정
        android:icon="@drawable/ic_home_black"		//아이콘 디자인
        android:title="Home"/> 		//화면에서 보여질 탭 이름
</menu>
```
다시 메인화면의 xml로 넘어와 bottom navigation 항목에 다음과 같이 코드를 입력해 menu 디렉토리에 있는 navigation.xml을 연결해준다.
```xml
app:menu="@menu/navigation"
```
여기까지 진행하면 네비게이션 바에 지정해준 아이콘을 가진 탭이 생성되는 것을 확인할 수 있다. 마지막으로 사용자가 선택한 탭을 강조하고 나머지 탭은 연하게 보이도록 selector resource를 사용한다. menu resource의 비슷하게 \<selector> 태그 아래에 \<item>을 작성하는데, 아래와 같이 탭이 체크됐을 때(state_checked = "true")와 체크되지 않았을 때(state_checked = "false") 다른 color 값을 가지도록 설정했다.
```xml
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:color="@color/black"		//선택됐을 때는 검정색
        android:state_checked="true"/>
    <item android:color="#C0C0C0"		//선택되지 않았을 때는 회색
        android:state_checked="false"/>
</selector>
```
또 다시 메인화면의 xml에서 bottom navigation 항목에 아래와 같이 탭의 색과 탭 이름의 색을 방금 작성한 xml 코드와 연결해주면 해당 탭을 선택했을 때 선택한 탭이 검정색으로 변하면서 눈에 띄게된다.
```xml
app:itemIconTint="@color/bottom_selector"
app:itemTextColor="@color/bottom_selector"
```
<br>[목차로 돌아가기](#Contents)<br><br>

### ViewPager
AppBar와 BottomNavigationView 사이에 위치한 공간에 ViewPager를 두었다. ViewPager는 사용자가 화면을 좌/우로 스와이프함에 따라 화면 뷰를 전환해주는데, 이 때 보통 Fragment를 활용한다. Fragment는 화면 UI의 일부를 변경시킬 수 있게하는 것으로, Activity는 화면 전체를 변경시킨다는 점에서 스와이프나 네비게이션의 아이콘 클릭으로 화면의 일부를 전환할 때는 Fragment로 구현한다. 따라서 3개의 탭을 눌렀을 때 나타날 Fragment를 각각 생성했다. 이렇게 만들어준 Fragment를 ViewPager에 보이게 하려면 Adapter가 필요하다. 따라서 먼저 다음과 같이 MainPagerAdapter라는 어뎁터를 생성한다.
```kotlin
class MainPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> HomeFragment()		//0번 탭이 선택되면 return HomeFragment()
            1 -> LibraryFragment()
            else -> MyPageFragment()
        }
    }

    override fun getCount() = 3		//뷰가 3개라는 뜻
    //override fun getCount() { return 3 }과 같다
}
```
이제 성공적으로 adapter를 만들었으니 ViewPager에 Fragment를 보여줄 차례이다. 이는 ViewPager를 담고 있는 MainActivity의 onCreate()에 아래처럼 adapter를 지정해주면 된다.
```kotlin
main_viewPager.adapter = MainPagerAdapter(supportFragmentManager)
main_viewPager.offscreenPageLimit = 2
```
여기서 두 번째 줄의 offscreenPageLimit은 Fragment의 이미지를 메모리에 저장해놓을 개수를 지정한다. 이 값이 1이라면 현재 페이지를 기준으로 바로 이전 페이지와 이후 페이지의 이미지를 저장하고, 값이 2라면 좌/우로 2페이지씩 저장한다. 이렇게 하는 이유는 페이지를 스와이프할 때 현재 뷰와 다음 뷰가 동시에 보이면서 슬라이딩되기 때문이라고한다. 현재 화면에 따른 메모리 저장 상태는 [여기](http://coolsharp.blogspot.com/2018/11/viewpager-offscreenpagelimit.html)에서 시각적으로 확인할 수 있다. 여기까지 진행하면 화면을 스와이프했을 때 적절한 화면이 보이는 것을 확인할 수 있다.
<br><br>
하지만 ViewPager로 화면을 전환했을 때는 탭 아이콘의 색이 바뀌지 않고, BottomNavigationView로 화면을 전환했을 때는 해당 fragment가 보이지 않는다. 따라서 아래와 같이 ViewPager로 화면이 전환되면 네이게이션뷰에 현재 페이지의 아이콘을 강조하도록하고, 네이게이션뷰로 화면이 전환되면 선택된 페이지의 레이아웃이 보이도록 ViewPager에 보여줄 화면의 인덱스를 작성하면 제대로 작동되는 것을 확인할 수 있다.
```kotlin
main_viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
    override fun onPageSelected(position: Int) {
        //네비게이션 메뉴 아이템 체크
        bottomNavigationView.menu.getItem(position).isChecked = true
    }

})

bottomNavigationView.setOnNavigationItemSelectedListener {
    when(it.itemId) {
        R.id.menu_home -> main_viewPager.currentItem = 0
        R.id.menu_book -> main_viewPager.currentItem = 1
        R.id.menu_person -> main_viewPager.currentItem = 2
    }
    true		//setOnNavigationItemSelectedListener()는 Boolean형을 리턴한다.
}
```
<br>[목차로 돌아가기](#Contents)<br><br>

### RecyclerView
RecyclerView는 동일한 구조를 가진 요소들이 반복될 때 사용한다. 이 프로젝트에서 스크롤을 내리면 사용자의 프로필 사진, 이름, 그리고 게시물이 반복해 등장하므로 이를 xml에 하나의 그룹으로 묶어준다. (여기서 묶어준 xml을 item이라고 부르기도 한다고 한다.) 이렇게 만든 레이아웃을 RecyclerView를 넣고 싶은 화면의 xml에 다음과 같이 작성한다.
```xml
<androidx.recyclerview.widget.RecyclerView
        ...
        app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
        android:orientation="vertical"
        tools:listitem="@layout/item_insta"/>
```
RecyclerView를 화면에 배치하는 방법에는 Linear와 Grid가 있는데, Linear로 배치를 하면 orientation을 vertical 혹은 horizontal로 설정해 세로로 혹은 가로로 배치한다. Grid로 배치한다면 바둑판 모양처럼 화면에 배치할 수 있다. 위의 코드에서 layoutManager에 Linear로 배치하기로 설정했고 그 방향은 vertical로 설정해 화면을 위/아래로 스크롤 내리면 같은 형태의 게시글이 나올 수 있도록했다.<br><br>
그 다음은 data class를 작성했다. data class는 getter와 setter를 따로 작성하지 않아도 되는 클래스로 불필요한 코드의 작성을 하지 않아도 된다는 장점이 있다. 이곳에 리사이클러뷰에 넣을 데이터인 userName, img_profile, 그리고 img_contents를 모두 String 형으로 선언했다.
```kotlin
data class InstaData (
    val userName : String,
    val img_profile : String,
    val img_contents : String
)
```
다음으로 데이터를 화면에 보여주는 역할을 하는 ViewHolder를 만들었고 ViewHolder와 실제 화면을 연결해주는 Adapter를 만들어줬다.
```kotlin
class InstaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tv_userName = itemView.findViewById<TextView>(R.id.tv_username)
    val img_profile = itemView.findViewById<ImageView>(R.id.img_profile)
    val img_contents = itemView.findViewById<ImageView>(R.id.img_contents)

    fun bind(instaData: InstaData) {        //data class. 실제 데이터를 화면에 넣어주는 역할
        tv_userName.text = instaData.userName
        Glide.with(itemView).load(instaData.img_profile).into(img_profile)
        Glide.with(itemView).load(instaData.img_contents).into(img_contents)
    }
}
```
위의 코드에서 itemView는 리사이클러뷰를 디자인한 xml 코드를 가리킨다. 이 값은 아래 코드인 InstaAdapter의 onCreateViewHolder()에서 이 클래스를 호출하면서 넘겨준다. 리사이클러뷰에 새성한 사용자 이름, 프로필 사진, 그리고 게시글 사진을 코드 상에서 연결하기 위해 findViewById를 해주었고 bind() 함수를 정의해 연결된 리사이클러뷰에 데이터를 넣어주는 코드를 작성했다.
```kotlin
class InstaAdapter(private val context: Context) : RecyclerView.Adapter<InstaViewHolder>() {
    var data = mutableListOf<InstaData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_insta, parent, false)
        return InstaViewHolder(view)
   }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: InstaViewHolder, position: Int) {
        holder.bind(data[position])     //n번 데이터를 ViewHolder에 묶어서 화면에 보여준다.
    }
}
```
RecyclerView.Adapter<T>()를 상속 받으면 onCreateViewHolder(), getItemCount(), onBindViewHolder()를 반드시 오버라이딩 해야하는데, 각각 ViewHolder에 xml을 연결하고, 리사이크러뷰에 넣을 데이터의 개수를 반환하며, 실제로 ViewHolder에서 정의한 bind()를 호출해 화면에 보여주는 역할을 한다. 마지막으로 리사이클러뷰를 띄우는 Activity 또는 Fragment에서 어뎁터를 연결해주고 데이터를 넣어주면 화면에 성공적으로 리사이클러뷰가 만들어진 것을 볼 수 있다. 아래는 Fragment에 어뎁터를 연결하는 코드의 일부이다.
```kotlin
val data = mutableListOf<InstaData>()   //데이터를 넣을 배열

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    instaAdapter = InstaAdapter(view.context)
    rv_home.adapter = instaAdapter      //xml에 어뎁터 연결
    loadData()      //데이터를 임의로 생성하고 어댑터에 전달
}

private fun loadData() {
    //데이터 입력
    data.apply {
        add(
            InstaData(
                userName = "name here",
                img_profile = "image source here",
                img_contents = "image source here"
            )
        )
    }
    //데이터 전달 및 어뎁터에 알림
    instaAdapter.data = data
    instaAdapter.notifyDataSetChanged()
}
```
<br>[목차로 돌아가기](#Contents)<br><br>

reference: [BottomNavigationView Library](https://material.io/develop/android/components/bottom-navigation-view/), [offscreenPageLimit 메모리 상태](http://coolsharp.blogspot.com/2018/11/viewpager-offscreenpagelimit.html)