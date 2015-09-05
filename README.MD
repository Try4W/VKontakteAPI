
## Описание:

Простой, легковестный биндинг VK API для Java

## Использование

*С авторизацией через access_token:*
```
    public static void main(String[] args) throws IOException {
        VkAuth vkAuth = new VkAuth(123456789/*ID вашего приложения*/, "messages"); // VkAuth помогает авторизовать пользователя
        System.out.println("Put it in your browser: " + vkAuth.getAuthUri());

        System.out.println("And paste response here:");
        String response = new Scanner(System.in).nextLine();

        vkAuth.generateAccessData(response); // VkAuth вытаскивает данные из ответа сервера и сохраняет их как AccessData
        AccessData accessData = vkAuth.getAccessData();
        System.out.println("Access token: " + accessData.getAccessToken());
        System.out.println("Expires in: " + accessData.getExpiresIn());
        System.out.println("User id: " + accessData.getUserId());

        VkApi vkApi = new VkApi(accessData.getAccessToken(), 5.37);

        VkMethod getDialogsMethod = new VkMethod("messages.getDialogs"); // Собираем метод
        getDialogsMethod.addArgument("count", "5");

        VKList<VKApiDialog> dialogs = new VKList<>(vkApi.invoke(getDialogsMethod), VKApiDialog.class); // Вызываем API и десериализуем её как список диалогов
        System.out.println("Dialogs:");
        dialogs.forEach(dialog -> System.out.println(" - " + dialog.message.user_id + ": " + dialog.message.body)); // Выводим список диалогов в консоль
    }
```

*Без авторизации:*
```
    private void test() throws IOException {
        VkMethod getWallMethod = new VkMethod("wall.get"); // Собираем метод
        getWallMethod.addArgument("owner_id", "1"); // Паша Дуров
        getWallMethod.addArgument("offset", "2"); // Смещение на 2
        getWallMethod.addArgument("count", "1"); // 1 пост

        VKList<VKApiPost> vkApiPosts = new VKList<>(VkApi.invoke(getWallMethod, "5.37"), VKApiPost.class);
        System.out.println(vkApiPosts.get(0).text); // Выводим третий пост со стены Паши Дурова
    }
```
