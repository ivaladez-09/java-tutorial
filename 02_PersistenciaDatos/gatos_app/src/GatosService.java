import com.google.gson.Gson;
import com.squareup.okhttp.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class GatosService {
    public static void verGatos() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();

        // Cortar corchetes
        String elJson = response.body().string();
        elJson = elJson.substring(1, elJson.length() - 1);

        //Parsear Json a un objeto Gato
        Gson gson = new Gson();
        Gatos gatos = gson.fromJson(elJson, Gatos.class);

        //Redimensionar
        Image image = null;
        try {
            URL url = new URL(gatos.getUrl());
            image = ImageIO.read(url);
            ImageIcon fondoGato = new ImageIcon(image);

            if(fondoGato.getIconWidth() > 800){
                Image fondo = fondoGato.getImage();
                Image modificada = fondo.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                fondoGato = new ImageIcon(modificada);
            }

            String menu = "Opciones: \n" +
                    "\n1. Ver otra imagen" +
                    "\n2. Favoritos" +
                    "\n3. Volver";

            String [] botones = {
                    "1. Ver otra imagen",
                    "2. Favorito",
                    "3. Volver"
            };

            String id_gato = gatos.getId();
            String opcion = (String) JOptionPane.showInputDialog(
                    null,
                    menu,
                    id_gato,
                    JOptionPane.INFORMATION_MESSAGE,
                    fondoGato,
                    botones,
                    botones[0]);

            int seleccion = -1;
            for (int i = 0; i < botones.length; i++) {
                if (opcion.equals(botones[i])){
                    seleccion = i;
                }
            }

            switch (seleccion){
                case 0:
                    verGatos();
                    break;
                case 1:
                    favoritoGato(gatos);
                    break;
                default:
                    break;
            }
        }catch (IOException e){
            System.out.println(e);
        }


    }

    public static void favoritoGato(Gatos gato) throws IOException {
        try{
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType,
                    "{\r\n    \"image_id\": \"" + gato.getId() + "\"\r\n}");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", gato.apikey)
                    .build();
            Response response = client.newCall(request).execute();

        }catch (IOException e){
            System.out.println(e);
        }

    }

    public static void verFavorito(String apikey){
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .method("GET", null)
                    .addHeader("x-api-key", apikey)
                    .build();
            Response response = client.newCall(request).execute();

            String elJson = response.body().string();
            Gson gson = new Gson();

            GatosFav [] gatosArray = gson.fromJson(elJson, GatosFav[].class);

            if (gatosArray.length > 0){
                int min = 1;
                int max = gatosArray.length;
                int aleatorio = (int) (Math.random() * ((max - min) + 1)) + min;
                int indice = aleatorio - 1;

                GatosFav gatoFav = gatosArray[indice];

                //Redimensionar
                Image image = null;
                URL url = new URL(gatoFav.getImage().getUrl());
                image = ImageIO.read(url);
                ImageIcon fondoGato = new ImageIcon(image);

                if(fondoGato.getIconWidth() > 800){
                    Image fondo = fondoGato.getImage();
                    Image modificada = fondo.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                    fondoGato = new ImageIcon(modificada);
                }

                String menu = "Opciones: \n" +
                        "\n1. Ver otra imagen" +
                        "\n2. Eliminar Favorito" +
                        "\n3. Volver";

                String [] botones = {
                        "1. Ver otra imagen",
                        "2. Eliminar Favorito",
                        "3. Volver"
                };

                String id_gato = gatoFav.getId();
                String opcion = (String) JOptionPane.showInputDialog(
                        null,
                        menu,
                        id_gato,
                        JOptionPane.INFORMATION_MESSAGE,
                        fondoGato,
                        botones,
                        botones[0]);

                int seleccion = -1;
                for (int i = 0; i < botones.length; i++) {
                    if (opcion.equals(botones[i])){
                        seleccion = i;
                    }
                }

                switch (seleccion){
                    case 0:
                        verFavorito(apikey);
                        break;
                    case 1:
                        borrarFavorito(gatoFav);
                        break;
                    default:
                        break;
                }
            }

        }catch (IOException e){
            System.out.println(e);
        }
    }

    public static void borrarFavorito(GatosFav gatoFav){
        try{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites/" + gatoFav.getId())
                    .delete(null)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", gatoFav.getApikey())
                    .build();
            Response response = client.newCall(request).execute();

        }catch (IOException e){
            System.out.println(e);
        }
    }
}
