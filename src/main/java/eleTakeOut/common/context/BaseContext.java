package eleTakeOut.common.context;

/**
 * 基于ThreadLocal封装工具类，用于保存和获取当前登录用户id
 */
public class BaseContext {

    public static ThreadLocal<Long> userThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<Long> adminThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<Long> shopThreadLocal = new ThreadLocal<>();

    public static void setCurrentUserId(Long id) {
        userThreadLocal.set(id);
    }

    public static Long getCurrentUserId() {
        return userThreadLocal.get();
    }

    public static void setCurrentAdminId(Long id) {
        adminThreadLocal.set(id);
    }

    public static Long getCurrentAdminId() {
        return adminThreadLocal.get();
    }

    public static void setCurrentShopId(Long id) {
        shopThreadLocal.set(id);
    }

    public static Long getCurrentShopId() {
        return shopThreadLocal.get();
    }

    public static void clear() {
        userThreadLocal.remove();
        adminThreadLocal.remove();
        shopThreadLocal.remove();
    }
}
