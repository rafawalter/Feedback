
package feedback;

class ExceptionHelper {
    public static void tratarException(Exception ex) throws RuntimeException {
        throw new RuntimeException(ex);
    }
}
