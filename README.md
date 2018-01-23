# MVVM
## MVVM是什么？
MVVM它分为三个层：

 - Model：数据模型，对数据进行处理
 - View：UI展示，包括Activity、Fragment等
 - ViewModel：View和Model之间的桥梁，处理界面逻辑，让View和Model之间进行交互。
![这里写图片描述](http://img.blog.csdn.net/20180123211010228?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvQV9zZW5keQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
## 为什么要有MVVM？
现在使用的基本是MVP或是MVC，最开始觉得MVP已经很好了，因为它通过Presenter把View和Model分隔开来，很大程度减低耦合性。但是慢慢发现MVP也存在着它的不足：
 -  接口多，而且粒度不好控制，分太小接口会很多，分太大代码又会很多。
 -   View层上的某个UI可能变更，数据映射到UI上可能也要跟着改变
 - P层可能会随着业务的增多而代码变多，View的方法也可能变多。这可能会导致他们依然会产生臃肿。
而MVVM的特点就是：
 - 可重用性：View的视图逻辑放在ViewModel中，多个View可重用这些视图逻辑
 - 双向绑定：可直接在布局中填充数据，而不用去获取View的实例再去setter。
## 说到MVVM，当然少不了DataBinding
### 介绍
DataBinding是谷歌提供的一个框架，它让我们省去了许多findViewById和View设置数据的操作。
### 使用
 - 在build.gradle文件中的android下添加dataBinding{enabled=true}
 - 	准备好数据模型：

```
public class User extends BaseObservable{
    private String name;
    private int arg;

    public User(String name, int arg) {
        this.name = name;
        this.arg = arg;
    }
    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);

    }
    @Bindable
    public int getArg() {
        return arg;
    }

    public void setArg(int arg) {
        this.arg = arg;
        notifyPropertyChanged(BR.arg);
    }
}

```
加上注解和notifyPropertyChanged(BR.xxx)是为了绑定的实体改变时布局中的数据跟着改变，还有另一种更方便的

```
public class Other {
    //效果和User的@Bindable+notifyPropertyChanged(BR.xxx)相同
    //通过set改变数据进而布局中绑定的数据跟着改变
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<Integer> age = new ObservableField<>();
}

```

 - 在布局文件中绑定数据

```
<layout xmlns:android="http://schemas.android.com/apk/res/android">
//在layout标签中导入User
<import
    alias="aUser" <!—别名，防止类名重复-- >
    type="com.sendi.mvvmdemo.bean.User"/>
<variable
    name="user"
    type="aUser"/>
	//绑定数据：
	<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_weight="1"
    android:gravity="center"
    android:text="@{user.name}" <!—绑定User类的name属性 -- >
    android:textColor="#000000"
    />
</layout>
```

 - 绑定点击事件
Java代码
```
public class MyClick {
    public void onChangeAge(View view){//次点击方法必须是public void 并且参数为View
        Toast.makeText(view.getContext(), "click me!", Toast.LENGTH_SHORT).show();
    }
}

```
布局文件

```
<variable
    name="myClick"
    type="com.sendi.mvvmdemo.MyClick"/>

<Button
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="changeAge"
    android:onClick="@{myClick.onChangeAge}"
    />

```

 - 图片的显示
 Java类
 

```
public class ImageUtil {
//此方法要static，上边主节点参数为布局文件中的属性（第二个参数可换成别的类型）
    @BindingAdapter({"image"})
    public static void imageLoader(ImageView imageView,int imageId){
        imageView.setImageResource(imageId);
    }
}

```
布局文件中

```
<ImageView
    android:layout_width="60dp"
    android:layout_height="60dp"
    image="@{imgId}"
    />

```
##### 接下来是设置绑定

 - 数据显示

```
//获取Binding
ActivityMainBinding mBinding
		=DataBindingUtil.setContentView(this,R.layout.activity_main);
//设置要显示的数据实体
User mUser= new User（”asendi”,21）  
mBinding.setUser(mUser);
```
这样数据就显示到界面中。

 - 动态改变数据

```
mUser.setAge(22);
//只要修改User对象，此时界面会跟着改变年龄的显示
```

 - 点击事件

```
mBinding.setMyClick(new MyClick());
//这样点击事件便失效了
```

 - 设置图片

```
mBinding.setImgId(R.mipmap.bg);//设置图片资源
```

 - 列表
	 - 这里用RecyclerView为例子，在布局中的RecyclerView与往常一样
 

```
mBinding.recyclerView //这样便是获取到RecyclerView的实例，而不需要去FVB；其中recyclerView是在布局中的id
```
item布局文件：

```
<?xml version="1.0" encoding="utf-8"?>
<layout>
    <data>
        <variable
            name="user"
            type="com.sendi.mvvmdemo.bean.User"/>
    </data>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:orientation="vertical"
              android:layout_width="match_parent"
              android:layout_height="wrap_content">
<TextView
    android:id="@+id/name"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@{user.name}"
    />
    <TextView
        android:id="@+id/age"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@{user.age}"
        />
</LinearLayout>
</layout>
```
适配器:

```
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private List<User> mUserList = new ArrayList<>();

    public MyAdapter() {}

    public void refresh(List<User> userList) {
        mUserList = userList;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MyHolder.create(
                LayoutInflater.from(parent.getContext()),parent);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.bindTo(mUserList.get(position));
    }


    @Override
    public int getItemCount() {
        if (mUserList==null)
            return 0;
        return mUserList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {

        private UserItemBinding mItemBinding;

        public MyHolder(UserItemBinding binding) {
            super(binding.getRoot());
            this.mItemBinding = binding;
        }

        public void bindTo(User user){
            mItemBinding.setUser(user);//将数据设置到布局文件中
            mItemBinding.executePendingBindings();//强行绑定数据

        }

        static MyHolder create(LayoutInflater inflater, ViewGroup parent) {
            UserItemBinding binding =		//获取一个item布局文件的Binding
                    UserItemBinding.inflate(inflater, parent, false);
            return new MyHolder(binding);
        }
    }
}

```
#### 在布局文件中还支持的表达式

 - 数学表达式：+=*/
 - 字符串拼接：+
 - 逻辑表达式：&& ||
 - 位操作符：^ | &
 - 一元操作法：+-！~
 - 位移操作符：>> << >>>
 - 比较操作符：== > >=  < <=
 - instanceof
 - 分组操作符：()
 - 强转、方法调用
 - 字面量：character、String、numeric、null
 - 字段的访问：User.name
 - 数组
 - 三元操作符：? :

以上便是浅浅的总结，希望可以指出不足之处，谢谢。


