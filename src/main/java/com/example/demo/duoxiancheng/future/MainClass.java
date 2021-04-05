package com.example.demo.duoxiancheng.future;

public class MainClass {


    public static void main(String[] args) {

        //Future演示
        /**
         * 该实例的目的是为了解决 单次通过数据库查询数据超时问题
         * 后来考虑到根据userId去数据库中进行查询可以提高效率
         * 就是先查出符合条件的userid集合
         * 根据userid分别开子线程去查询该userid下的所有人脸记录
         *
         * FutureTask可以用来做闭锁
         * Future.get取决于任务的状态
         * 它主要的功能是 等所有根据userid执行查询的sql语句都执行完了，相当于完成一个存到list中，完成一个存到list中
         * 最后才统一根据这个list集合进行后续的操作
         */


//        List<Future<List<FacePlusPlusResponseDTO>>> ans = new ArrayList<>();
//
//        for (String userId : userIdList){
//            FutureTask<List<FacePlusPlusResponseDTO>> futureTask = new FutureTask<>(new FaceCallable2ZeroTask(this, riskAccountDataCollectionRequestDTO, userId));
//            //线程池执行
//            threadPooConfig.execute(futureTask);
//            ans.add(futureTask);
//        }
//
//
//        List<FacePlusPlusResponseDTO> facePlusPlusResult = new ArrayList<>();
//
//        //isDone方法表示任务是否已经完成，若任务完成，则返回true
//        //get()方法用来获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回
//        for (Future<List<FacePlusPlusResponseDTO>> i : ans) {
//            List<FacePlusPlusResponseDTO> tmp = i.get();
//            facePlusPlusResult.addAll(tmp);
//        }
    }
}
