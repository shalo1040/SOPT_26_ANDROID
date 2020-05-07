package com.example.gridlayoutmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var webtoonAdapter : WebtoonAdapter
    var data = mutableListOf<WebtoonData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //구분선
        val horizontalDivider = DividerItemDecoration(this, 0);
        rv_webtoon.addItemDecoration(horizontalDivider);

        val verticalDivider = DividerItemDecoration(this, 1);
        rv_webtoon.addItemDecoration(verticalDivider);

        webtoonAdapter = WebtoonAdapter(this)
        rv_webtoon.adapter = webtoonAdapter
        loadData()
    }

    private fun loadData() {
        data.apply {
            add(WebtoonData(
                image = "https://search.pstatic.net/common?type=o&size=157x120&quality=75&direct=true&src=https%3A%2F%2Fshared-comic.pstatic.net%2Fthumb%2Fwebtoon%2F183559%2Fthumbnail%2Fthumbnail_IMAG08_2d4031df-0144-43dd-81bf-38af64ba1bf4.jpg",
                title = "신의 탑", score = "★ 9.9", author = "SIU"))
            add(WebtoonData(
                image = "https://search.pstatic.net/common?type=o&size=157x120&quality=75&direct=true&src=https%3A%2F%2Fshared-comic.pstatic.net%2Fthumb%2Fwebtoon%2F654774%2Fthumbnail%2Fthumbnail_IMAG08_db5bd3ba-be69-4dfe-9555-b0a0bc9b2867.jpg",
                title = "소녀의 세계", score = "★ 10.0", author = "모랑지"))
            add(WebtoonData(
                image = "https://search.pstatic.net/common?type=o&size=157x120&quality=75&direct=true&src=https%3A%2F%2Fshared-comic.pstatic.net%2Fthumb%2Fwebtoon%2F602910%2Fthumbnail%2Fthumbnail_IMAG08_6cabf8c4-9874-46bf-97ba-4fea19636caa.jpg",
                title = "윈드브레이커", score = "★ 9.9", author = "조용석"))
            add(WebtoonData(
                image = "https://search.pstatic.net/common?type=o&size=157x120&quality=75&direct=true&src=https%3A%2F%2Fshared-comic.pstatic.net%2Fthumb%2Fwebtoon%2F702422%2Fthumbnail%2Fthumbnail_IMAG08_6d0b4c87-e15f-48f4-92f4-f08f3d8e4346.jpg",
                title = "니편내편", score = "★ 9.9", author = "미티"))
            add(WebtoonData(
                image = "https://search.pstatic.net/common?type=o&size=157x120&quality=75&direct=true&src=https%3A%2F%2Fshared-comic.pstatic.net%2Fthumb%2Fwebtoon%2F597478%2Fthumbnail%2Fthumbnail_IMAG08_f79eb360-dd45-4b8e-aa18-2d87e966f3f3.jpg",
                title = "평범한 8반", score = "★ 9.9", author = "영파카"))
            add(WebtoonData(
                image = "https://search.pstatic.net/common?type=o&size=157x120&quality=75&direct=true&src=https%3A%2F%2Fshared-comic.pstatic.net%2Fthumb%2Fwebtoon%2F730174%2Fthumbnail%2Fthumbnail_IMAG08_28e0cff2-69fe-4e9c-8d74-90b861f2e470.jpg",
                title = "칼가는 소녀", score = "★ 10.0", author = "오리"))
            add(WebtoonData(
                image = "https://search.pstatic.net/common?type=o&size=71x77&quality=75&direct=true&src=https%3A%2F%2Fshared-comic.pstatic.net%2Fthumb%2Fwebtoon%2F694946%2Fthumbnail%2Fthumbnail_IMAG10_c5e84842-ae12-4497-899a-c7289b4ede49.jpg",
                title = "귀전구담", score = "★ 10.0", author = "QTT"))
            add(WebtoonData(
                image = "https://search.pstatic.net/common?type=o&size=157x120&quality=75&direct=true&src=https%3A%2F%2Fshared-comic.pstatic.net%2Fthumb%2Fwebtoon%2F733074%2Fthumbnail%2Fthumbnail_IMAG08_0545e531-7ab2-4536-8bf7-e0f40ae4badf.jpg",
                title = "백수세끼", score = "★ 9.9", author = "치즈"))
            add(WebtoonData(
                image = "https://search.pstatic.net/common?type=o&size=157x120&quality=75&direct=true&src=https%3A%2F%2Fshared-comic.pstatic.net%2Fthumb%2Fwebtoon%2F709731%2Fthumbnail%2Fthumbnail_IMAG08_c459a912-81b9-44e9-bace-31774077ac9f.jpg",
                title = "유일무이 로맨스", score = "★ 10.0", author = "두부"))
            add(WebtoonData(
                image = "https://search.pstatic.net/common?type=o&size=157x120&quality=75&direct=true&src=https%3A%2F%2Fshared-comic.pstatic.net%2Fthumb%2Fwebtoon%2F743025%2Fthumbnail%2Fthumbnail_IMAG08_da22edc3-3c42-478e-8aad-2a08e6b19529.jpg",
                title = "야자괴담", score = "★ 9.9", author = "이도광"))
            add(WebtoonData(
                image = "https://search.pstatic.net/common?type=o&size=157x120&quality=75&direct=true&src=https%3A%2F%2Fshared-comic.pstatic.net%2Fthumb%2Fwebtoon%2F698888%2Fthumbnail%2Fthumbnail_IMAG08_fa4bada5-24b7-495b-93f5-cf4e8c750fd3.png",
                title = "이것도 친구라고", score = "★ 10.0", author = "제야"))
            add(WebtoonData(
                image = "https://search.pstatic.net/common?type=o&size=157x120&quality=75&direct=true&src=https%3A%2F%2Fshared-comic.pstatic.net%2Fthumb%2Fwebtoon%2F730811%2Fthumbnail%2Fthumbnail_IMAG08_8a3433fc-1945-4d3b-b6f5-00fd67b5d5a0.jpg",
                title = "사소한 냐냐", score = "★ 9.9", author = "LICO"))
        }
        webtoonAdapter.data = data
        webtoonAdapter.notifyDataSetChanged()
    }
}
