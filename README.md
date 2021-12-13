# NewsApi-Android-MVVM
 
Hi, I created Android News app using <a href="https://newsapi.org/">NewsAPI</a> and Jetpack Component.

## Used Libraries

<ul>
 <li><a href="https://developer.android.com/jetpack/androidx">AndroidX</a></li>
 <li><a href="https://developer.android.com/guide/navigation">Navigation</a>(Fragment transitions)</li>
 <li><a href="https://developer.android.com/topic/libraries/data-binding">Data binding</a>  (Bind views and data)</li>
 <li><a href="https://developer.android.com/topic/libraries/architecture/viewmodel">ViewModel</a> (Store and manage UI-related data)</li>
 <li><a href="https://developer.android.com/topic/libraries/architecture/livedata">LiveData</a> (Observable data)</li>
 <li><a href="https://dagger.dev/hilt/">Hilt</a> (Dependency Injection)</li>
 <li><a href="https://github.com/square/retrofit">Retrofit</a> (HTTP client)</li>
 <li><a href="https://github.com/google/gson">Gson</a> (JSON to object converter)</li> 
 <li><a href="https://github.com/bumptech/glide">Glide</a> (to load image)</li> 
 <li><a href="https://github.com/intuit/sdp">sdp</a> & <a href="https://github.com/intuit/ssp">ssp (font size)</a> (to adaptive font and view size)</li> 
</ul>

## Architecture

This app uses MVVM architecture where the guideline you can read by <a href="https://developer.android.com/jetpack/docs/guide">here</a>.

Beside MVVM, this application is also use a single-activity application architecture. All screen transitions are done by <a href="https://developer.android.com/guide/navigation?hl=ja">Navigation</a>.

That because the design and the requirement dosen't need a complex thing. 

A single-activity can make better on 
<ul>
<li>Screen transition</li>
<li>Sharing-data using ViewModel</li>
<li>Passing argument safely with Safe Args Gradle Plugin</li>
</ul>
