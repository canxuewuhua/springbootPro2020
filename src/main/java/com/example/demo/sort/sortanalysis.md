
# 排序算法

  ## 冒泡排序 快速排序
  基本有序时 用直接插入或者冒泡排序 直接插入对大部分已有序时较好 冒泡排序对于n较小时较好
  当比较乱的时候用希尔排序
  
  n较大的时候使用O(n*logn)  如快排 堆排序 归并排序
  快排平均时间复杂度为O(n*logn) 最坏情况是位O(n2) 在基本有序的情况下 而堆排序都是O(n*logn)
  
  二分查找 必须是有序情况 最坏情况是O(n*logn) 最好的是O(1)
  
  稳定的是冒泡排序
  不稳定的是快排
  
  冒泡排序时间复杂度  平均和最坏都是O(n方) 最好的情况是O(n)有序情况下