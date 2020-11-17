package com.modulo.proyectobd.basesDatos;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.modulo.proyectobd.basesDatos.models.Moto;
import com.modulo.proyectobd.basesDatos.models.MotoDao;
import com.modulo.proyectobd.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Add databese entity
@Database(entities = {Moto.class}, version = 1, exportSchema = false)
public abstract class RoomBD extends RoomDatabase {

    public abstract MotoDao mainDao();

    private static volatile RoomBD INSTANCE;
    private static final int NUMBER_OF_THREADS = 5;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    private static RoomBD.Callback sRoomDatabaseCallback = new RoomBD.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                MotoDao dao = INSTANCE.mainDao();
                dao.deleteAll();

                Moto moto = new Moto(999,
                                    2019,
                                    201,
                                    8,
                                    7,
                                    9,
                                    8,
                                    8,
                                    4 ,
                                    115,
                                    180,
                                    20000,
                                    "Aprilia RSV4 RR 2018",
                                    "43 mm full ajustable",
                                    "Aprilia V4 65°, 4-triempos, cuatro valvulas por cilindro.",
                                    "Delantero: 330 mm doble disco perforado con pastilla Brembo M50. Trasero Monodisco, pinza flotante de un émbolo, 220 mm de diámetro ",
                                    "Control de tracción, Control Anticaballito, Launch-Control, quick-Shifter, velocidad_crucero,, pit_limiter, ABS,",
                                    "Aclamada unánimemente por la crítica y codiciada por los motociclistas de todo el mundo, la RSV4 es el símbolo indiscutible del segmento y el referente en cuanto a deportividad y prestaciones. También es un brillante ejemplo de lo que la experiencia de un equipo de competición de éxito puede aportar, 54 campeonatos mundiales a sus espaldas en el transcurso de un espacio de tiempo relativamente corto. Las habilidades perfeccionadas en el circuito y transferidas a la línea de producción han permitido a los motoristas de todo el mundo experimentar las increíbles sensaciones y la emoción de Aprilia Racing.",
                                    "SuperSport",
                                    "Aprilia",
                                    R.drawable.aprilia_rsv4_2019);
                dao.insert(moto);

                Moto moto2 = new Moto(999,
                        2015,
                        199,
                        8,
                        8,
                        9,
                        7,
                        8,
                        4 ,
                        113,
                        176,
                        19950,
                        "BMW S1000RR 2015",
                        "Horquilla invertida de 46 mm con ajuste en rebote y comprensión. Recorrido de 120 mm",
                        "4 cilindros 80 × 49.7 mm con inyeccion electroica",
                        "Delantero: Doble disco, pinzas flotantes, pinza fija de 4 pistones, 320 mm de diámetro. Trasero Monodisco, pinza flotante de un émbolo, 220 mm de diámetro ",
                        "BMW Motorrad ABS, ABS PRO, DTC (Dynamic Traction Control), HSC (Hill Start Control), 4 modos de conducción (Rain, Road, Dynamic, Race), Asistente de cambio Pro, Pantalla TFT, Intermitente LED, Faro y luz rasera LED",
                        "Cualquiera que la vea se da cuenta inmediatamente: es sinónimo de máximas prestaciones. Cada pieza de la RR se ha diseñado para sacar el máximo de ella. La disposición del bastidor, incluida la nueva tecnología de chasis, hace que la RR sea más ligera y precisa. A esto se suma una posición de asiento ergonómicamente perfecta, que no tiene parangón debido a los cambios en el diseño del bastidor y del carenado. Los faros LED de serie le confieren un aspecto absolutamente dinámico y agresivo. Los intermitentes delanteros han pasado del carenado a los retrovisores, que no solo son estéticos, sino que también mejoran la visibilidad. En la parte trasera, la RR domina en términos de diseño y funcionalidad: \"Todo en uno\" es el lema. Además de la iluminación de la matrícula, el soporte de la matrícula ahora también incluye intermitentes LED, luz trasera y luz de freno.",
                        "Sport",
                        "BMW",
                        R.drawable.bmw_s1000rr_2015);
                dao.insert(moto2);

                Moto moto3 = new Moto(773,
                        2015,
                        48,
                        4,
                        4,
                        3,
                        5,
                        3,
                        2 ,
                        60,
                        196,
                        9399,
                        "Kawasaki W800 2015",
                        "Horquilla invertida de 46 mm con ajuste en rebote y comprensión. Recorrido de 120 mm",
                        "4 cilindros 80 × 49.7 mm con inyeccion electroica",
                        "Delantero: Doble disco, pinzas flotantes, pinza fija de 4 pistones, 320 mm de diámetro. Trasero Monodisco, pinza flotante de un émbolo, 220 mm de diámetro ",
                        "BMW Motorrad ABS, ABS PRO, DTC (Dynamic Traction Control), HSC (Hill Start Control), 4 modos de conducción (Rain, Road, Dynamic, Race), Asistente de cambio Pro, Pantalla TFT, Intermitente LED, Faro y luz rasera LED",
                        "La W800, al igual que las W800 STREET y W800 CAFE, es una motocicleta que cautiva a una amplia variedad de pilotos gracias a su maniobrabilidad ligera y a sus exquisitos acabados (kodawari). La W800 destaca especialmente de entre estos tres modelos vintage actualizados, postulándose como la más fiel a la W original, tanto a nivel estético como en cuanto a las sensaciones que ofrece al piloto, quien a su vez siente el placer y el orgullo de conducir una maravilla de los años 60. Con la llegada de la W800, los pilotos en busca de un sabor de historia, ahora pueden disfrutar de 3 sabores de W",
                        "Nacked",
                        "Kawasaki",
                        R.drawable.bmw_s1000rr_2015);
                dao.insert(moto3);

            });
        }
    };


    // Define database name

    public static String DATABASE_NAME = "BaseDeDatos";

    public static RoomBD getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomBD.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomBD.class, DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }




}
