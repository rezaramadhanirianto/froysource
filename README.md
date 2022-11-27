<h1 align="center">Froysource</h1></br>
<p align="center">
Easier to manage resource from Network or Local for Android
</p>


### How to install

```gradle
build.gradle (project)

repositories{
    maven { url 'https://jitpack.io' }
}
```


```gradle
build.gradle (module)

dependencies{
    implementation "com.github.rezaramadhanirianto:froysource:1.1.2"
}
```

### How to use
#### Only from Network (flow and retrofit)
Declare your model
```kotlin
    // model for handle error if API status code > 400
    class YourErrorResponse: ErrorResponse() {
        @SerializedName("code")
        override var code: Int = 0

        @SerializedName("message")
        override var message: String = ""
    }

    data class YourModel(val id: String)
```
First wrap data with class <code>Resource</code>, and check the status
```kotlin
    // retrofit function
    // Responses is model from retrofit
    fun loadNetwork: Responses<YourModel> 

    fun loadFromNetwork(){
        return BaseCall.call(YourErrorResponse::class.java){ loadFromNetwork() }
    }

    val res = loadFromNetwork()

    when(res){
        is Resource.Default -> {} // State first default
        is Resource.Loading -> {} // State loading if needed
        is Resource.Success -> {} // state if success
        is Resource.Error -> {} // state if error
    }
```
#### From network and local
```kotlin
// first generic is input data and second generic is output data
return object : NetworkBoundResource<YourModel, YourModel>(){
    // data from API
    override suspend fun createCall(): Flow<Resource<YourModel>> {
        return loadFromNetwork()
    }

    // mapping data from network model to view model
    override fun mapToResultType(data: YourModel): YourModel? {
        return data
    }

    // is no need local storage
    override fun noNeedLocalSource(): Boolean {
        return false
    }

    // should fetch from API
    override fun shouldFetch(data: YourModel): Boolean {
        return true
    }

    // should load from DB
    override fun loadFromDB(): Flow<YourModel>? {
        return localDatabase.movieDao().getPopular(page)
    }

    // save to local db (room)
    override suspend fun saveCallResult(data: YourModel) {
        localDatabase.yourDao().insertAllReplace(data)
    }

    // deprecated
    // on failed get from API
    override fun onFetchFailed() {
        println("do something")
    }

}.asFlow()
```
