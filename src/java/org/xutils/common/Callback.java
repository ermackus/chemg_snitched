package org.xutils.common;

public interface Callback
{
    public interface CacheCallback<ResultType> extends CommonCallback<ResultType>
    {
        boolean onCache(final ResultType p0);
    }
    
    public interface Callable<ResultType>
    {
        void call(final ResultType p0);
    }
    
    public interface Cancelable
    {
        void cancel();
        
        boolean isCancelled();
    }
    
    public static class CancelledException extends RuntimeException
    {
        public CancelledException(final String s) {
            super(s);
        }
    }
    
    public interface CommonCallback<ResultType> extends Callback
    {
        void onCancelled(final CancelledException p0);
        
        void onError(final Throwable p0, final boolean p1);
        
        void onFinished();
        
        void onSuccess(final ResultType p0);
    }
    
    public interface GroupCallback<ItemType> extends Callback
    {
        void onAllFinished();
        
        void onCancelled(final ItemType p0, final CancelledException p1);
        
        void onError(final ItemType p0, final Throwable p1, final boolean p2);
        
        void onFinished(final ItemType p0);
        
        void onSuccess(final ItemType p0);
    }
    
    public interface PrepareCallback<PrepareType, ResultType> extends CommonCallback<ResultType>
    {
        ResultType prepare(final PrepareType p0);
    }
    
    public interface ProgressCallback<ResultType> extends CommonCallback<ResultType>
    {
        void onLoading(final long p0, final long p1, final boolean p2);
        
        void onStarted();
        
        void onWaiting();
    }
}
