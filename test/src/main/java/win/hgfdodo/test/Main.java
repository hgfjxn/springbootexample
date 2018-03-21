package win.hgfdodo.test;

class Solution {

  public String convert(String s, int numRows) {
    int l = s.length();
    if (l < 2 || numRows >= l || numRows ==1) {
      return s;
    }
    int columns = l / (2 * numRows - 2) * (numRows - 1);
    int tmp = l - (2 * numRows - 2) * (columns / (numRows - 1));
    if (tmp <= numRows) {
      columns += 1;
    } else {
      columns += 1 + tmp - numRows;
    }

    StringBuilder sb = new StringBuilder();
    for (int r = 0; r < numRows; r++) {
      for (int i = 0; i < columns; i++) {
        int x = i / ( numRows - 1);//整块列数
        if(x*(2*numRows-2)+r >=l){
          break;
        }
        if (i % (numRows - 1) == 0) {
          int index = i + x * (numRows - 1) + r;
          sb.append(s.charAt(index));
          continue;
        }
        if ((i + r) % (numRows - 1) == 0) {

          int re = i-x * (numRows - 1);//剩余列数
          int index = x *(2 * numRows - 2);
          if(re == 0){
            index += r;
          }else{
            index +=numRows+re-1;
          }
          sb.append(s.charAt(index));
        }
      }
    }

    return sb.toString();
  }


  public static void main(String[] args) {
    Solution solution = new Solution();
    String x = solution.convert("PAYPALISHIRING", 4);
    System.out.println(x);
  }
}