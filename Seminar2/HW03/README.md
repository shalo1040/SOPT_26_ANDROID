# HW03
### HW03 Demo
<img src="/img/seminar2/gridLayout.gif" alt="hw03 demo" width="350"><br><br>

### GridLayoutManager
그리드 형식의 RecyclerView를 사용하기 위해 xml에서 layoutManager를 GridLayoutManager로 설정하고 가로에 그리드 3개를 넣기 위해 spanCount에 3값을 주었다.
```xml
<androidx.recyclerview.widget.RecyclerView
    ...
    app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"
    app:spanCount="3"/>
```

### RecyclerView
xml에 RecyclerView를 만든 후 뷰의 xml, 넣어줄 데이터 클래스, ViewHolder, Adapter를 만들었는데, 이 과정은 지난 과제와 같으므로 [링크](https://github.com/shalo1040/sopt_android/tree/master/Seminar2/HW01%20%2B%20HW02#RecyclerView)로 대체한다.

### 시행착오
앱을 완성하고 실행 시켰더니 텍스트는 넣어준 값 그대로 잘 나왔던 반면, 이미지는 링크를 불러오지 못하고 아무것도 출력되지 않았다. 라이브러리를 잘못 넣어준건지 다시 확인해보았지만 여전히 이미지를 불러오는 데 실패했다. 그러던 중 에러가 발생해 Logcat에서 빌드 에러를 확인하면서 Glide로 이미지를 불러오는 데 실패했다는 부분 또한 읽게 되었고, 그 이유는 인터넷 접근 허용을 하지 않았기 때문이라는 것을 알게됐다. 따라서 AndroidManifest 파일에서 인터넷을 사용할 수 있도록 <uses-permission android:name="android.permission.INTERNET"/>을 적어주고 다시 실행했더니 이미지를 성공적으로 불러오는 것을 확인할 수 있었다. 다른 코드의 문제인지 계속 확인했었는데 앞으로는 Manifest도 잊지 않고 확인해야겠다.