package com.example.cibertecproject;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.cibertecproject.MainActivity.CHANNEL_ID;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    static Boolean running=true;// creamos este boolean para parar y activar el servicio
    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method


    @Override
    protected void onHandleIntent(Intent intent) {
        //Obtenemos la Data
        ArrayList<String> schedules=intent.getStringArrayListExtra("fechas");
        ArrayList<String> name=intent.getStringArrayListExtra("name");
        ArrayList<String> noList=new ArrayList<String>();//Creamos unlista de ban para separar los items que ya tuvieron su notificacion de que ya empieza el evento
        ArrayList<String> noList2=new ArrayList<String>();//creamos una lista de ban para separar los items que ya tuvieron su notificacion de asistencia

        int size=name.size();// obtenemos el tamaño de la lista para podere recorrerlo

        while(running){
            for(int i=0;i<size;i++){
                try {
                    //obtenemos el valor de la fecha de la lista y del momento actual
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    Date fecha=sdf.parse(schedules.get(i));
                    Date fechaActual=c.getTime();
                    //---------------
                    long diff=fecha.getTime()-fechaActual.getTime();// calculamos la diferencia en milisegundos
                    if(diff<=30*60*1000 && diff>=0){
                        //ponemos el codigo para revisar si esta dentro de los 30 min antes del evento
                        Boolean proof=true;// un boolean para saber el item esta en la lista de ban
                        int size2=noList.size();// tamaño de la lista de ban para poder recorrerla
                        for(int j=0;j<size2;j++){
                            if(name.get(i).equals(noList.get(j))){
                                proof=false;// le damos el valor de false al boolean si es que el item esta en la lista de ban
                            }
                        }
                        if(proof){// si no esta en la lista de ban se hace lo siguiente
                            // se le agrega a la lista de ban y se manda la notificacion
                            noList.add(name.get(i));
                            showNotification(this,notificationOfEvent(name.get(i),"El evento comienza  en menos de 30min"),i);
                        }

                    }else{
                        // lo mismo que arriba solo que para avisar de que tiene que pasar lista;
                        Boolean proof1=true;
                        if(diff<=-30*60*1000){
                            int size1=noList2.size();
                            for(int j=0;j<size1;j++){
                                if(name.get(i).equals(noList2.get(j))){
                                    proof1=false;
                                }
                            }
                            if(proof1){
                                noList2.add(name.get(i));
                                showNotification(this,notificationOfEvent(name.get(i),"Necesita pasar lista en el Evento"),i);
                            }
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        stopSelf();
    }
    public static void showNotification(Context context,Notification notification, int id){
        NotificationManagerCompat managerCompat =
                NotificationManagerCompat.from(context);
        managerCompat.notify(id, notification);
    }

    public Notification notificationOfEvent(String titulo,String text){
        Intent nextIntent = new Intent(this,
                AddCourseEventActivity.class);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(MainActivity.class);
        taskStackBuilder.addNextIntent(nextIntent);

        PendingIntent pendingIntent =
                taskStackBuilder.getPendingIntent(0,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setColor(Color.parseColor("#333c87"))
                        .setContentTitle(titulo)
                        .setContentText(text)
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);
        return builder.build();
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */

}
