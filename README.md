# ShimmerRecyclerView for Android

[![JitPack](https://jitpack.io/v/appuraja1/ShimmerRecyclerView.svg)](https://jitpack.io/#appuraja1/ShimmerRecyclerView)
[![API](https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=23)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)
[![Platform](https://img.shields.io/badge/Platform-AndroidX-orange.svg)](https://developer.android.com/jetpack/androidx)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0%2B-purple.svg)](https://kotlinlang.org)

An efficient, lightweight custom **Android RecyclerView** with integrated **shimmer animation** and **skeleton loading placeholder** views. Seamlessly indicate background data fetching without messy layout toggling or boilerplate adapter logic. Fully compatible with **AndroidX**, Kotlin, Java, and modern Gradle builds.

Powered by the hardware-accelerated **Facebook Shimmer** rendering pipeline for stutter-free 120Hz display support.

---

## Key Features

* **Zero-Boilerplate Skeleton Screens:** Toggle loading placeholders with simple `showShimmerAdapter()` and `hideShimmerAdapter()` methods.
* **Hardware Accelerated:** Built-in Facebook Shimmer engine ensures smooth rendering on high-refresh-rate screens.
* **Flexible Layout Managers:** Native support for vertical, horizontal, and grid layout styles directly from XML.
* **Dual Animation Modes:** Supports both full-item shimmer and selective masking (view background based).
* **AndroidX & Java Ready:** 100% interoperable with both modern Kotlin extensions and legacy Java codebases.

---

## Visual Preview

| List Shimmer View | Grid Skeleton View |
| :---: | :---: |
| <img src="screenshots/list_demo.gif" alt="Android RecyclerView List Shimmer Effect" width="260" /> | <img src="screenshots/grid_demo.gif" alt="Android Grid Skeleton Loading View" width="260" /> |

| Selective Mask (List) | Selective Mask (Grid) |
| :---: | :---: |
| <img src="screenshots/second_list_demo.gif" alt="Android Shimmer Masking Effect" width="260" /> | <img src="screenshots/second_grid_demo.gif" alt="Android Skeleton Grid Shimmer" width="260" /> |

---

## Quick Setup & Installation

### Step 1: Add JitPack Repository
Add JitPack to your project's root `settings.gradle`:

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url '[https://jitpack.io](https://jitpack.io)' }
    }
}
```

### Step 2: Add Gradle Dependency
Add the library dependency to your app module's `build.gradle`:

```groovy
dependencies {
    implementation 'com.github.appuraja1:ShimmerRecyclerView:v1.1.0'
}
```

---

## How to Use

### 1. Define XML Layout
Add `ShimmerRecyclerView` directly to your layout XML and specify your skeleton placeholder view:

```xml
<com.appuraja.views.shimmer.ShimmerRecyclerView
    xmlns:android="[http://schemas.android.com/apk/res/android](http://schemas.android.com/apk/res/android)"
    xmlns:app="[http://schemas.android.com/apk/res-auto](http://schemas.android.com/apk/res-auto)"
    android:id="@+id/shimmerRecyclerView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:shimmer_demo_child_count="8"
    app:shimmer_demo_grid_child_count="2"
    app:shimmer_demo_layout="@layout/layout_demo_grid"
    app:shimmer_demo_layout_manager_type="grid"
    app:shimmer_demo_angle="20"
    app:shimmer_demo_duration="1000" />
```

### 2. Control Shimmer in Activity or Fragment

#### Kotlin Example
```kotlin
val shimmerRecyclerView = findViewById<ShimmerRecyclerView>(R.id.shimmerRecyclerView)

// 1. Trigger shimmer loading while fetching data
shimmerRecyclerView.showShimmerAdapter()

// 2. Hide shimmer and render actual data when ready
viewModel.data.observe(viewLifecycleOwner) { dataList ->
    myActualAdapter.submitList(dataList)
    shimmerRecyclerView.adapter = myActualAdapter
    shimmerRecyclerView.hideShimmerAdapter()
}
```

#### Java Example
```java
ShimmerRecyclerView shimmerRecyclerView = findViewById(R.id.shimmerRecyclerView);

// 1. Show skeleton loader
shimmerRecyclerView.showShimmerAdapter();

// 2. Set actual adapter and dismiss skeleton loader once loaded
shimmerRecyclerView.setAdapter(myActualAdapter);
shimmerRecyclerView.hideShimmerAdapter();
```

---

## XML Attributes & Customization

Fine-tune your skeleton loading states using these attributes:

| XML Attribute | Programmatic Method | Default | Description |
| :--- | :--- | :---: | :--- |
| `app:shimmer_demo_layout` | `setDemoLayoutReference(resId)` | — | **(Required)** Layout resource used as the skeleton placeholder. |
| `app:shimmer_demo_child_count` | `setDemoChildCount(count)` | `10` | Total number of dummy placeholder items to display. |
| `app:shimmer_demo_layout_manager_type` | `setDemoLayoutManager(type)` | `linear_vertical` | Layout mode: `linear_vertical`, `linear_horizontal`, or `grid`. |
| `app:shimmer_demo_grid_child_count` | `setGridChildCount(count)` | `2` | Column span count when using `grid` layout. |
| `app:shimmer_demo_duration` | `setDemoShimmerDuration(ms)` | `1000` | Duration of one full shimmer animation sweep in milliseconds. |
| `app:shimmer_demo_angle` | — | `0` | Angle tilt of the shimmer wave line (between `0` and `30` degrees). |
| `app:shimmer_demo_mask_width` | `setDemoShimmerMaskWidth(float)` | `0.5` | Width ratio of the animated light gradient (`0.0` to `1.0`). |
| `app:shimmer_demo_shimmer_color` | — | `#A0FFFFFF` | Tint color of the animated shimmer sweep line. |
| `app:shimmer_demo_reverse_animation`| — | `false` | When `true`, sweeps right-to-left instead of left-to-right. |
| `app:shimmer_demo_view_holder_item_background` | — | transparent | Background drawable/color applied behind selective shimmer masks. |

---

## ProGuard & R8 Optimization

Rules are bundled via consumer configurations, but if manual keep rules are preferred:

```pro
-keep class com.appuraja.views.shimmer.** { *; }
-keep class com.facebook.shimmer.** { *; }
```

---

## Maintainer & Contributions

* **Appu Raja** - [bookboard.co@gmail.com](mailto:bookboard.co@gmail.com)

Pull requests, feature recommendations, and issue reports are welcome. Please star the repository if it helps your Android project!

---

## License

```text
Copyright 2026 Appu Raja

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   [http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
