# Android - Kotlin 기초

### 1. TextView

안드로이드 XML 파일에서 텍스트를 나타내는 View이다. 코틀린파일에서 id값을 통해 setText(String)과 같은 명령어로 텍스트를 변경할 수 있다.



### 2. EditText

Text를 입력받는 View로, hint를 사용해 어떤 형태인지 나타낼 수 있다. ConstraintLayout에서는 Plain Text로 쓰인다.



### 3. Button

클릭을 통해 반응하는 View로, 코틀린 파일에서  id값과 setOnclickListener{}를 통해, 클릭되었을 때의 액션을 설정할 수 있다.

```kotlin
// button이 클릭되면 editText에 들어있는 Text를 가져와 textView에 Text를 변경.
button.setOnclickListener{
    var resultText: String = editText.text.toString()
    TV.setText(resultText)
}
```



### 4. ImageView

이미지파일을 보여주는 View, 코틀린 파일에서 id값과 setImageResource(파일경로)를 통해 이미지를 설정할 수 있다.

```kotlin
// btn이 클릭되면, id_profile를 x로 변경한다.
btn.setOnClickListener{
    id_profile.setImageResource(R.drawable.x)
}
```



### 5. Toast

토스트키에서 토스트가 나오는 것과 같이 메시지가 출력되는 형식에서 비롯된 어원. 메시지 출력시간에 따라, 짧게는 LENGTH_SHORT와 길게는 LENGTH_LONG 두가지 형태로 나뉜다.

```kotlin
Toast.makeText(this@MainActivity, "토스트!", Toast.LENGTH_SHORT).show()
```



### 6. Intent

인텐트는 앱 컴포넌트가 무엇을 할 것인지를 담는 메시지 객체이다. 이러한 메시지를 사용하는 목적은 다른 액티비티, 서비스, 브로드캐스트 리시버, 컨텐트 프로바이더 등을 실행하는 것이다. 즉 인텐트는 그들 사이에 데이터를 주고 받기 위한  용도로 쓰인다.



#### 화면전환

화면은 하나의 액티비티로 생각할 수 있다. 즉, 화면간의 이동하는 과정은 각가의 액티비티를 필요에 따라 띄우거나 단는 과정과 같다.

 어플리케이션을 구성하다보면 메인 액티비티에서 띄워야 할 화면들이 여러개가 될 뿐만 아니라 띄웠던 화면을 닫고, 원래의 메인 화면으로 돌아올 때 데이터를 새로 적용해야 하는 경우 등 다양한 케이스를 고려하여 작업하여야 합니다. 즉 단순히 액티비티를 띄워주는 것이 아니라 어떤 액티비티를 띄운 것인지 그리고 띄웠던 액티비티로부터의 응답을 받아 처리하는 코드가 필요하다. 액티비티를 띄우기 위해 사용되는 메서드는 **startActivity()**와 **startActivityForResult()**이 정의되어 있다.



화면전환을 하기 위해서는 Manifest파일에 전환할 Activity를 추가해 주어야 한다.

```xml
<!-- AndroidManifest.xml -->
<activity android:name= ".SubActivity" >
```

```kotlin
// MainActivity.kt
package com.example.intentkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toSub.setOnClickListener {

            val intent = Intent(this, SubActivity::class.java) // SubActivity를 받아옴
            startActivity(intent) // SubActivity 시작
            finish() // MainActivity 종료
        }
    }
}
```



### 7. WebView

웹앱이나, 웹문서를 불러올 때 사용하는 View. 이를 수행하기 위해서는, 인터넷 퍼미션을 얻어야 한다.

```xml
<!-- 인터넷 퍼미션(권한) -->
<uses-permission android:name="android.permission.INTERNET">
<application
             ...
             android:theme="@style/Theme.AppCombat.NoActionBar"> <!-- 액션바 삭제 -->
```



MainActivity에서는 웹이 정상적으로 작동하도록 자바 스크립트를 허용하고, 새 창이 뜨지 않도록 방지하는 구문을 작성하고, 링크 주소를 load 하면 된다.

```kotlin
package com.example.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.settings.javaScriptEnabled = true // 자바 스크립트 허용

        /* 웹뷰에서 새 창이 뜨지 않도록 방지하는 구문 */
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        /* 웹뷰에서 새 창이 뜨지 않도록 방지하는 구문 */

        webView.loadUrl("https://www.naver.com") // 링크 주소를 load 함
    }

    override fun onBackPressed() {
        if(webView.canGoBack()) webView.goBack()
        else super.onBackPressed()
    }
}
```



### 8. ListView

사용자가 정의한 데이터 목록을 아이템 단위로 구성하여 화면에 출력하는 ViewGroup의 한 종류이다. 아이템들은 세로 방향으로 나열되며, 아이템의 갯수가 많아짐에 따라 스크롤 기능을 사용해 표시 기준 위치를 이동시킬 수 있다.



ListView에 표시되는 아이템은 단순히 Text만 출력하는 구조가 될 수 잇고, Image, Button, CheckBox 등 여러 View의 조합으로 구성되는 좀 더 복잡한 형태(Custom)가 될 수도 있다.

```kotlin
// User.kt
package com.exmple.listView

class User (val profile: Int, val name: String, val age: String, val greet: String)
// profile이미지 뷰가 Int인 이유는 resource파일이 int형으로 되어 있기 때문.
```



#### Adapter

ListView에 데이터를 추가하여 화면에 표시하기 위해서는 Adapter를 사용해야 한다. **어댑터는 사용자가 정의한 데이터를 ListView에 출력하기 위해 사용하는 객체로, 사용자 데이터와 화면 출력 View로 이루어진 두 개의 부분을 이어주는 객체**이다.

```kotlin
// UserAdapter.kt
package com.example.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class UserAdapter (val context: Context, val UserList: ArrayList<User>): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_user, null)

        val profile = view.findViewById<ImageView>(R.id.imageView)
        val name = view.findViewById<TextView>(R.id.Name)
        val age = view.findViewById<TextView>(R.id.age)
        val greet = view.findViewById<TextView>(R.id.greeting)

        val user = UserList[position]

        profile.setImageResource(user.profile)
        name.text = user.name
        age.text = user.age
        greet.text = user.greet

        return view
    }

    // 현재 위치에 있는 Item 가져옴.
    override fun getItem(position: Int): Any {
        return UserList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    // UserList의 크기를 가져옴.
    override fun getCount(): Int {
        return UserList.size
    }

}

```

```kotlin
// MainActivity.kt
package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var UserList = arrayListOf<User>(
        User(R.drawable.ic_launcher_foreground, "대창너무조아", "23", "ㅎㅇㅎㅇ")
        ,User(R.drawable.ic_launcher_foreground, "내장너무조아", "23", "ㅎㅇㅎㅇ")
        ,User(R.drawable.ic_launcher_foreground, "막창너무조아", "23", "ㅎㅇㅎㅇ")
        ,User(R.drawable.ic_launcher_foreground, "곱창너무조아", "23", "ㅎㅇㅎㅇ")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val item = arrayOf("Apple", "Pear", "Watermelon", "Melon")
//        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, item)


        val Adapter = UserAdapter(this, UserList)
        listView.adapter = Adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener{parent, view, position, id ->
            val selectItem = parent.getItemAtPosition(position) as User
            Toast.makeText(this, selectItem.name, Toast.LENGTH_SHORT).show()
        }
    }
}
    // UserList 작성
    var UserList = arrayListOf<User>(
        User(R.drawable.ic_launcher_foreground, "대창너무조아", "23", "ㅎㅇㅎㅇ")
        ,User(R.drawable.ic_launcher_foreground, "내장너무조아", "23", "ㅎㅇㅎㅇ")
        ,User(R.drawable.ic_launcher_foreground, "막창너무조아", "23", "ㅎㅇㅎㅇ")
        ,User(R.drawable.ic_launcher_foreground, "곱창너무조아", "23", "ㅎㅇㅎㅇ")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val item = arrayOf("Apple", "Pear", "Watermelon", "Melon")
//        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, item)


        // listView에 UserAdapter 연결
        val Adapter = UserAdapter(this, UserList)
        listView.adapter = Adapter

        // listView에 아이템이 클릭되었을 때
        listView.onItemClickListener = AdapterView.OnItemClickListener{parent, view, position, id ->
            val selectItem = parent.getItemAtPosition(position) as User
            Toast.makeText(this, selectItem.name, Toast.LENGTH_SHORT).show()
        }
    }
}
```



### 8. RecyclerView

ListView의 상위 호환. RecyclerView 위젯에 엑세스하려면 다음과 같이 프로젝트에 v7 지원 라이브러리를 추가해야 합니다.

```gradle
// build.gradle  dependencies 섹션

dependencied {
	implementation 'androidx.recyclerview:recyclerview:1.1.0'
}
```

```kotlin
// Profiles.kt
package com.example.recyclerView

class Profiles(val gender:Int, val name:String, val age: Int, val job: String)
```

```kotlin
// ProfileAdapter.kt
package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class ProfileAdapter(val profileList: ArrayList<Profiles>) : RecyclerView.Adapter<ProfileAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener {
                val curPos: Int = adapterPosition
                val profile: Profiles = profileList.get(curPos)

                Toast.makeText(parent.context, "${profile.name}님을 팔로우하시겠습니까?", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    override fun onBindViewHolder(holder: ProfileAdapter.CustomViewHolder, position: Int) {
        holder.gender.setImageResource(profileList.get(position).gender)
        holder.name.text = profileList.get(position).name
        holder.age.text = profileList.get(position).age.toString()
        holder.job.text = profileList.get(position).job
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val gender = itemView.findViewById<ImageView>(R.id.id_profile) // 성별
        val name = itemView.findViewById<TextView>(R.id.tv_name) // 이름
        val age = itemView.findViewById<TextView>(R.id.tv_age) // 나이
        val job = itemView.findViewById<TextView>(R.id.tv_job) // 직업
    }

}
```

```kotlin
// MainActivity.kt
package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val profileList = arrayListOf(
            Profiles(R.drawable.userman, "대창너무조아", 23, "컴퓨터비전전문가"),
            Profiles(R.drawable.userwoman, "내장너무조아", 22, "비전전문가"),
            Profiles(R.drawable.userman, "막창너무조아", 26, "안드로이드 앱 개발자"),
            Profiles(R.drawable.userman, "곱창너무조아", 29, "IOS 앱 개발자"),
            Profiles(R.drawable.userwoman, "염통너무조아", 22, "플러터 앱 개발자"),
            Profiles(R.drawable.userwoman, "고기너무조아", 21, "리액트 앱 개발자"),
            Profiles(R.drawable.userwoman, "삼겹너무조아", 24, "하이브리드 앱 개발자"),
            Profiles(R.drawable.userwoman, "한우너무조아", 26, "웹앱 개발자"),
            Profiles(R.drawable.userman, "살치너무조아", 28, "반응형 웹 개발자"),
            Profiles(R.drawable.userman, "새우너무조아", 20, "유니티 앱 개발")
        )

        rv_profile.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_profile.setHasFixedSize(true)

        rv_profile.adapter = ProfileAdapter(profileList)
    }
}
```



### 9. SharedPreference

간단한 설정 값이나 문자열 같은 데이터들을 DB에 저장하지 않고 SharedPreferences를 사용해 데이터를 파일로 저장하는 방법.

```kotlin
package com.example.shareddb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
    }

    private fun loadData() {
        val pref = getSharedPreferences("pref", 0)
        et_hello.setText(pref.getString("name", "")) // 1번째 인자에서는 저장할 당시의 키 값을 적어줌, 2번째는 키 값에 데이터가 존재하지 않을 경우 대체 데이터를 적어둠.
    }

    override fun onDestroy() { // 해당 액티비티가 종료되는 시점이 다가올 때 호출
        saveData()
        super.onDestroy()
    }

    private fun saveData() {

        val pref = getSharedPreferences("pref", 0)
        val edit = pref.edit()  // 수정모드
        edit.putString("name", et_hello.text.toString()) // 1번째 인자에는 키 값, 2번째 인자에는 실제 담아둠
        edit.apply()
    }
}
```



### 10. NavigationMenu

NavigationMenu는 보통 앱 좌측상단에 줄 3개 아이콘이 있는 메뉴이다. 이를 만들기 위해서는 menu태그가 있는 xml 파일을 생성해 이를 적용시켜야 한다.

```xml
<!-- navi_menu.xml -->

<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">

    <group android:checkableBehavior="single">

        <item android:id="@+id/access"
            android:icon="@drawable/ic_baseline_accessibility_24"
            android:title="접근성"/>

        <item android:id="@+id/email"
            android:icon="@drawable/ic_baseline_email_24"
            android:title="이메일"/>

        <item android:id="@+id/message"
            android:icon="@drawable/ic_baseline_send_24"
            android:title="메시지"/>



    </group>
</menu>
```

```kotlin
<!-- activity_main.xml -->
<?xml version="1.0" encoding="utf-8"?>
<androidx.drawerlayout.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/layout_drawer"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">


    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Hello World!"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

        <ImageView
            android:id="@+id/btn_navi"
            android:layout_width="50dp"
            android:layout_height="50dp"
            android:layout_marginStart="8dp"
            android:layout_marginLeft="8dp"
            android:layout_marginTop="8dp"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            android:src="@drawable/ic_baseline_menu_64" />
    </androidx.constraintlayout.widget.ConstraintLayout>

    <com.google.android.material.navigation.NavigationView
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:id="@+id/naviView"
        app:menu="@menu/navi_menu"/>

</androidx.drawerlayout.widget.DrawerLayout>
```

```kotlin
// MainActivity.kt
package com.example.navigationmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_navi.setOnClickListener {
            layout_drawer.openDrawer(GravityCompat.START) // START : left, END : Right
        }

        naviView.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean { // 네비게이션 메뉴 아이템 클릭 시 수행
        when(item.itemId){
            R.id.access -> Toast.makeText(applicationContext, "접근성", Toast.LENGTH_SHORT).show()
            R.id.email -> Toast.makeText(applicationContext, "이메일", Toast.LENGTH_SHORT).show()
            R.id.message -> Toast.makeText(applicationContext, "메시지", Toast.LENGTH_SHORT).show()
        }
        layout_drawer.closeDrawers() // 네비게이션 뷰 닫기
        return false
    }

    override fun onBackPressed() {
        if(layout_drawer.isDrawerOpen(GravityCompat.START)){
            layout_drawer.closeDrawers()
        }
        else{
            super.onBackPressed()
        }
    }
}
```



### 11. Fragment

자체 수명 주기를 가지고, 자체 입력 이벤트를 받으며, 액티비티 실행 중에 추가 및 제거가 가능한 액티비티의 모듈식 섹션이다. (다른 액티비티에 재사용가능한 하위 액티비티와 같은 개념)

```kotlin
// Fragment1.kt
package com.example.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class Fragment1 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag1, container, false)
        return view
    }
}
```



```kotlin
// Fragment2.kt
package com.example.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class Fragment2 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag2, container, false)
        return view
    }
}
```



```kotlin
// Fragment3.kt
package com.example.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class Fragment3 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag3, container, false)
        return view
    }
}
```



```kotlin
// MainActivity.kt
package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var a : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_fragment1.setOnClickListener {
            a++
            Log.d("Fragment", a.toString())
            setFrag(0)
        }

        btn_fragment2.setOnClickListener {
            Log.d("Fragment2", "Fragment2 is activated!")
            setFrag(1)
        }

        btn_fragment3.setOnClickListener {
            Log.d("Fragment3", "Fragment3 is activated!")
            setFrag(2)
        }
    }

    private fun setFrag(fragNum : Int) {
        val ft = supportFragmentManager.beginTransaction()
        when(fragNum){
            0 -> ft.replace(R.id.main_frame, Fragment1()).commit()
            1 -> ft.replace(R.id.main_frame, Fragment2()).commit()
            2 -> ft.replace(R.id.main_frame, Fragment3()).commit()
        }
    }

}
```
# Android - Firebase

