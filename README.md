This project demonstrates how to set up and use Android Jetpack's Navigation Component with Kotlin. It focuses on navigating between fragments within a single activity.
## Features

- Kotlin-based navigation between fragments
- Single activity architecture using NavHostFragment.
- Argument passing between fragments through bundle.



## Deployment

Add following dependency
```bash
val nav_version = "2.8.0"
implementation("androidx.navigation:navigation-fragment:$nav_version")
implementation("androidx.navigation:navigation-ui:$nav_version")
```



## Steps
Add a NavHostFragment to activity_main.xml

Set up Navigation in the res/navigation folder

Label the Graph as per Transaction Requirements

Navigate Between Fragments Using findNavController().navigate()
```javascript
findNavController().navigate(R.id.action_fragmentA_to_fragmentB)
```
Arguments passing between fragments
```javascript
val bundle = Bundle()
bundle.putString("fromFragmentA" , "Welcome to Fragment B")
findNavController().navigate(R.id.action_fragmentA_to_fragmentB,bundle)
```
Use of animations during transaction (optional)
```javascript
fun NavOptions.Builder.setFadeAnimation(): NavOptions.Builder {
    return this.setEnterAnim(R.anim.fade_in)
        .setExitAnim(R.anim.fade_out)
        .setPopEnterAnim(R.anim.fade_in)
        .setPopExitAnim(R.anim.fade_out)
}

val fadeAnimation = NavOptions.Builder()
            .setFadeAnimation()
            .build()
findNavController().navigate(R.id.action_fragmentA_to_fragmentC,bundle,fadeAnimation)

```
