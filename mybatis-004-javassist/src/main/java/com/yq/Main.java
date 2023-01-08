package com.yq;

public class Main {
    public static int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int i =0,j=0,n=0,p=0;
        int max=0;
        char[] c = new char[len];

        int[] num = new int[len];

        if(len!=0)
            num[0]=0;
        for(i=0;i<len;i++){
            n=0;
            c[i] = s.charAt(i);
            num[i]=0;

            for(j=p;j<=i;j++){
                n++;
                if(c[i]==c[j]&&j!=i){
                    num[i]=j-i;
                    p=i+1;
                }
            }
            // if(c[i]==c[i-1]){
            //     num[i]=1;
            //     p=i;
            // }
            if(num[i]==0){
                num[i]=n;
            }
            if(max<num[i]){
                max=num[i];
            }
        }
        return max;
    }
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length, k = (m + n) / 2;
        int i, j, t = 0;
        int n1=0,n2=0;
        if(m>0){
            n1 = nums1[0];
        }else{
            if(n>0){
                if(( n) % 2 == 0){
                    return ((double)nums2[n/2-1]+(double)nums2[n/2])/2;
                }else{
                    return nums2[n/2];
                }
            }
        }

        if(n>0){
            n2 = nums2[0];
        }else{
            if(m>0){
                if(( m) % 2 == 0){
                    return ((double)nums1[m/2-1]+(double)nums1[m/2])/2;
                }else{
                    return nums1[m/2];
                }
            }
        }

        int a = n1, b = n2;
        for (i = 0, j = 0; i < m || j < n;) {
            if(i < m && j < n){
                if (n1 < n2) {
                    a = n1;
                    if(i<m-1){
                        n1 = nums1[++i];
                    }else {
                        i++;
                    }


                } else {
                    a = n2;
                    if(j<n-1){
                        n2 = nums2[++j];
                    }else {
                        j++;
                    }

                }
            } else if (i>=m&&j<n) {
                a=n2;
                if(j<n-1){
                    n2 = nums2[++j];
                }else {
                    j++;
                }
            } else if (j>=n&&i<m) {
                a=n1;
                if(i<m-1){
                    n1 = nums1[++i];
                }else {
                    i++;
                }
            }


            if ((m + n) % 2 == 0) {
                if (t == k) {
                    return ((double) a + (double) b) / 2;
                }
            } else {
                if (t == k) {
                    return a;
                }
            }

            b = a;
            t++;
        }
        return 0;
    }


    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{10000}, new int[]{10001}));

    }

}