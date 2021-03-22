### 解题思路
此处撰写解题思路
1.该题本质就是遍历字符然后将字符放置到不同的位置。
2.如果该行数为一行则直接返回该字符串
3.如果行数大于2则需要判断该字符放到哪一行，故需要一个字符串数组，每一个字符串代表一行，刚开始都置于空串，然后遍历s，利用sustring截取出来的还是字符串,然后考虑将该截取出来的字符串放置到哪一行利用down作为标签，初始值为false,判断遍历的方向，true就向下，false就向右,怎样驱动down进行方向的改变？通过定义的字符串数组的下标，下标为0或下标为numRows-1时就改变方向，改变方向后字符串数组的下标也要相应的++或--。
4.最后定义一个空串将各行中的字符串连接2起来，说是各行，只不过将字符串数组竖着看,本质还是一维字符串数组.

### 代码

```java
class Solution {
    public String convert(String s, int numRows) {
        if(numRows == 1) {
    		return s;
    	}
    	boolean down = false;
    	int loc = 0;
    	//需要把每一行都看成一个字符串。所以需要定义一个字符串数组
    	String[] rows = new String[numRows];//多少行就多少个字符串
    	//要将字符串按照要求放入到每一行的字符串中，所以要每一行的字符串都需要初始化
    	for(int i = 0;i < rows.length; i++) {
    		rows[i] = "";//做字符串的拼接
    	}
    	for(int i = 0;i < s.length() ;i++) {
    		rows[loc] = rows[loc] + s.substring(i,i+1);//将第一个字符串拼接到第一行中
    		//第一个字符串拼接到第一行后需要修改down
    		//什么时候需要修改down，什么时候会向下,边界会修改方向
    		if(loc == 0 || loc == numRows-1) {
    			down = !down;//true表示向下
    		}
    		
    		if(down) {
    			loc++;
    		}else {
    			loc--;
    		}	
    	}
    	//将每一行的字符串从左到右从上到下连接起来
    	String str = "";
    	for(String srow : rows) {
    		str += srow;
    	}
    	return str;
    }
}
```