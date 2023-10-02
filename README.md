# Groupie

Groupie is a simple, flexible library for complex RecyclerView layouts.

Groupie lets you treat your content as logical groups and handles change notifications for you — think sections with headers and footers, expandable groups, blocks of vertical columns, and much more.

It makes it easy to handle asynchronous content updates, insertions, and user-driven content changes.

At the item level, it abstracts away the boilerplate of item view types, item layouts, viewholders, and span sizes.

Groupie supports [View Binding](https://developer.android.com/topic/libraries/view-binding) and legacy `findViewById` binding.

<a href="https://raw.githubusercontent.com/chimbori/groupie/main/demo.mp4" target="_blank">
  <img src="https://raw.githubusercontent.com/chimbori/groupie/main/demo.gif" width="270" height="600">
</a>

## Get Started

### Project Setup

Groupie uses semantic versioning. If the API changes, then the major version will be incremented.
Upgrading from one minor version to the next minor version within the same major version should not require any client code to be modified.
The latest release is available via [GitHub Releases](https://github.com/chimbori/groupie/releases).

1.  Project/`build.gradle.kts`

    ```kotlin
    allprojects {
      repositories {
        // …
        maven { url "https://jitpack.io" }
        // …
      }
    }
    ```

2.  Module/`build.gradle.kts`

    ![https://img.shields.io/github/v/release/chimbori/groupie](https://img.shields.io/github/v/release/chimbori/groupie)

    ```kotlin
    dependencies {
      // …
      implementation("com.github.chimbori:groupie:0.0.0")  // Use the latest version number from above.
      // …
    }
    ```

3.  [View Binding](https://developer.android.com/topic/libraries/view-binding) (optional but highly recommended). Use **one** of the two steps below.

    - Module/`build.gradle.kts` (to enable View Binding for specific modules)
    ```kotlin
    android {
      buildFeatures {
        viewBinding = true
      }
    }
    ```

    - Project/`gradle.properties` (to enable View Binding for all modules in the project)
    ```properties
    android.defaults.buildfeatures.viewbinding=true
    ```

## Concepts & Sample Code

Use a `GroupieAdapter` anywhere you would normally use a `RecyclerView.Adapter`, and attach it to your RecyclerView as usual.

`SomeActivity.kt` or `SomeFragment.kt`:
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
  // …

  val groupieAdapter = GroupieAdapter()
  recyclerView.adapter = groupieAdapter

  // …
}
```

### Groups

Groups are the building blocks of Groupie.
An individual `Item` (the unit which an adapter inflates and recycles) is a Group of one.
You can add Groups and Items interchangeably to the adapter.

```kotlin
groupieAdapter += HeaderItem()
groupieAdapter += CommentItem()

val section = Section()
section.setHeader(HeaderItem())
section.addAll(bodyItems)
groupieAdapter += section
```

Modifying the contents of the `GroupieAdapter` in any way automatically sends change notifications.
Adding an item calls `notifyItemAdded()`; adding a group calls `notifyItemRangeAdded()`, etc.

Modifying the contents of a Group automatically notifies its parent.
When notifications reach the GroupieAdapter, it dispatches final change notifications.
There’s never a need to manually notify or keep track of indices, no matter how you structure your data.

There are a few simple implementations of Groups within the library:

- `Section`, a list of body content with an optional header group and footer group.
  It supports diffing and animating moves, updates, and other changes.
- `ExpandableGroup`, a single parent group with a list of body content that can be toggled hidden or shown.

Groupie tries not to assume what features your groups require.
Instead, groups are flexible and composable.
They can be combined and nested to arbitrary depth.

You can implement the `Group` interface directly if you want.
However, in most cases, you can extend `Section` or the base implementation, `NestedGroup`.
`Section` supports common RecyclerView paradigms like diffing, headers, footers, and placeholders.
`NestedGroup` provides support for arbitrary nesting of groups, registering/unregistering listeners, and fine-grained change notifications to support animations and updating the adapter.

### Items

Groupie abstracts away the complexity of multiple item view types.
Each Item declares a view layout id, and gets a callback to `bind` the inflated layout.

The `Item` class gives you simple callbacks to bind your model object to the generated binding.

`R.layout.item_card`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:foreground="?android:attr/selectableItemBackground">

  <TextView
    android:id="@+id/item_card_text"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />

</FrameLayout>
```

Class `ItemCardBinding` will be auto-generated when `viewBinding = true`.

`CardItem.kt`
```kotlin
class CardItem : BindableItem<ItemCardBinding>() {
  override fun getLayout() = R.layout.item_card
  override fun initializeViewBinding(view: View) = ItemCardBinding.bind(view)
  override fun bind(viewBinding: ItemCardBinding, position: Int) {
    viewBinding.itemCardText.text = "Hello, World!"
  }
}
```

Items can also declare their own column span and whether they are draggable or swipeable.

## Contributing

### Pull Requests

There is always lots of room for improvement, and we will gladly review your pull requests in a timely manner.

To maintain the integrity of the library, we have a few simple expectations from all code submitted.

1. Before sending a pull request, please open an issue to discuss your changes. Maintainers will offer feedback and
   help validate your proposal and overall design before you spend any time writing code.
1. Groupie is fully unit-tested, and we want to keep it that way. All new code should include unit tests.
1. All current tests should continue to pass. Either update the tests in the same commit, or modify new code so that
   existing tests continue to pass.
1. Changes should be self-contained as far as possible. When implementing multiple independent improvements, each one
   should be in its own commit.

### Bug Reports

Please attach a *minimal* sample project or code which reproduces the bug. Screenshots are also a huge help if the
problem is visual.

## License

    MIT License

    Copyright (c) 2016, Lisa Wray
    Copyright (c) 2023, Chimbori

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
