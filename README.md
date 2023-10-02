# Groupie

<a href="https://raw.githubusercontent.com/chimbori/groupie/main/demo.mp4" target="_blank">
  <img style="margin-left:48px;float:right" src="https://raw.githubusercontent.com/chimbori/groupie/main/demo.gif" width="270" height="600">
</a>

Groupie is a simple, flexible library for complex RecyclerView layouts.

Groupie lets you treat your content as logical groups and handles change notifications for you — think sections with headers and footers, expandable groups, blocks of vertical columns, and much more.

It makes it easy to handle asynchronous content updates, insertions, and user-driven content changes.

At the item level, it abstracts away the boilerplate of item view types, item layouts, viewholders, and span sizes.

Groupie supports [View Binding](https://developer.android.com/topic/libraries/view-binding) and legacy `findViewById` binding.

## Get Started

### Project Setup

Groupie uses semantic versioning. If the API changes, then the major version will be incremented.
Upgrading from one minor version to the next minor version within the same major version should not require any client code to be modified.
The latest release is available via [GitHub Releases](https://github.com/chimbori/groupie/releases).

1.  Project/`build.gradle.kts`

    ```kotlin
    allprojects {
      repositories {
        maven { url "https://jitpack.io" }
      }
    }
    ```

2.  Module/`build.gradle.kts`

    ![https://img.shields.io/github/v/release/chimbori/groupie](https://img.shields.io/github/v/release/chimbori/groupie)

    ```kotlin
    dependencies {
      implementation("com.github.chimbori:groupie:0.0.0")  // Use the latest version number from above.
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


Kotlin
```kotlin
val adapter = GroupieAdapter()
recyclerView.adapter = adapter
```

Java
```java
GroupieAdapter adapter = new GroupieAdapter();
recyclerView.setAdapter(adapter);
```
    
## Groups

Groups are the building block of Groupie.  An individual `Item` (the unit which an adapter inflates and recycles) is a Group of 1.  You can add Groups and Items interchangeably to the adapter.

Kotlin
```kotlin
groupAdapter += HeaderItem()
groupAdapter += CommentItem()

val section = Section()
section.setHeader(HeaderItem())
section.addAll(bodyItems)
groupAdapter += section
```

Java
```java
groupAdapter.add(new HeaderItem());
groupAdapter.add(new CommentItem());

Section section = new Section();
section.setHeader(new HeaderItem());
section.addAll(bodyItems);
groupAdapter.add(section);
```
    
Modifying the contents of the GroupieAdapter in any way automatically sends change notifications.  Adding an item calls `notifyItemAdded()`; adding a group calls `notifyItemRangeAdded()`, etc.

Modifying the contents of a Group automatically notifies its parent.  When notifications reach the GroupieAdapter, it dispatches final change notifications.  There's never a need to manually notify or keep track of indices, no matter how you structure your data.

```java
section.removeHeader(); // results in a remove event for 1 item in the adapter, at position 2
```
    
There are a few simple implementations of Groups within the library:
- `Section`, a list of body content with an optional header group and footer group.  It supports diffing and animating moves, updates and other changes
- `ExpandableGroup`, a single parent group with a list of body content that can be toggled hidden or shown.
    
Groupie tries not to assume what features your groups require.  Instead, groups are flexible and composable.  They can be combined and nested to arbitrary depth.  
    
Life (and mobile design) is complicated, so groups are designed so that making new ones and defining their behavior is easy. You should make many small, simple, custom groups as the need strikes you.

You can implement the `Group` interface directly if you want.  However, in most cases, you can extend `Section` or the base implementation, `NestedGroup`.  Section supports common RV paradigms like diffing, headers, footers, and placeholders.  NestedGroup provides support for arbitrary nesting of groups, registering/unregistering listeners, and fine-grained change notifications to support animations and updating the adapter.
    
## Items

Groupie abstracts away the complexity of multiple item view types.  Each Item declares a view layout id, and gets a callback to `bind` the inflated layout.  That's all you need; you can add your new item directly to a `GroupieAdapter` and call it a day.

### Item with data binding:

The `Item` class gives you simple callbacks to bind your model object to the generated binding.  Because of data binding, there's no need to write a view holder.  

```java
public class SongItem extends BindableItem<SongBinding> {

    public SongItem(Song song) {
        this(song);
    }    

    @Override public void bind(SongBinding binding, int position) {
        binding.setSong(song);
    }

    @Override public int getLayout() {
        return R.layout.song;
    }
}
```

If you're converting existing ViewHolders, you can reference any named views (e.g. `R.id.title`) directly from the binding instead. 
```java
    @Override public void bind(SongBinding binding, int position) {
        binding.title.setText(song.getTitle());
    }
```

You can also mix and match `BindableItem` and other `Items` in the adapter, so you can leave legacy viewholders as they are by making an `Item<MyExistingViewHolder>`.  

### Legacy item (your own ViewHolder)
You can leave legacy viewholders as they are by converting `MyExistingViewHolder` to extend `GroupieViewHolder` rather than `RecyclerView.ViewHolder`. Make sure to change the imports to `com.xwray.groupie.Item` and `com.xwray.groupie.GroupieViewHolder`.

Finally, in your `Item<MyExistingViewHolder>`, override 

```java
    @Override
    public MyExistingViewHolder createViewHolder(@NonNull View itemView) {
        return new MyExistingViewHolder(itemView);
    }
```

### Note: 

Items can also declare their own column span and whether they are draggable or swipeable.  

### Note:


## View binding

Add to your app module's `build.gradle`:

```gradle
android {
    buildFeatures {
        viewBinding true
    }
}

dependencies {
    implementation "com.github.lisawray.groupie:groupie:$groupie_version"
    implementation "com.github.lisawray.groupie:groupie-viewbinding:$groupie_version"
}
```

Then:

```kotlin
class MyLayoutItem: BindableItem<MyLayoutBinding>() {
    override fun initializeViewBinding(view: View): MyLayoutBinding {
        return MyLayoutBinding.bind(view)
    }

    // Other implementations...
}
```

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
