package ito.candiafloresjacob.horoscopo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.SmsManager;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] smsMessage = null;
        if (null != bundle) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            smsMessage = new SmsMessage[pdus.length];
            for (int i = 0; i < smsMessage.length; i++) {
                smsMessage[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                String telefono = smsMessage[i].getOriginatingAddress();
                String fechaEstablecida = smsMessage[i].getMessageBody().toString();
                Log.d("Número!!!", telefono);
                Log.d("Fecha recibida del cuerpo!!", fechaEstablecida);
                String horoscopo = horoscopoSeleccionado(fechaEstablecida);
                String prediccion = prediccionSeleccionada();
                String msg;
                if (horoscopo == "El numero de digitos es incorrecto" || horoscopo == "Fecha incorrecta") {
                    msg = "" + horoscopo;
                } else {
                    msg = "Su signo maya es: " + horoscopo + "\n";
                    msg = "\nPredicción del día: \n";
                    msg = msg + prediccion;
                    Log.d("MENSAJE PARA ENVIAR", msg);
                }
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(telefono, null, msg, null, null);
            }
        }
    }

    private String horoscopoSeleccionado(String date) {
        int largo = date.length();
        String signo = "";
        if (largo < 4 || largo > 4) {
            signo = "El numero de digitos es incorrecto";
        } else {
            int fecha = Integer.parseInt(date);
            int mes = fecha % 100;
            int dia = fecha / 100;

            if (mes > 0 && mes < 13) {
                if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
                    if (dia > 0 && dia < 32) {
                        if (mes == 1) {
                            if (dia <= 9)
                                signo = signo + "Lagarto";
                            else
                                signo = signo + "Mono";
                        } else {
                            if (mes == 3) {
                                if (dia <= 6)
                                    signo = signo + "Halcon";
                                else
                                    signo = signo + "Jaguar";
                            } else {
                                if (mes == 5) {
                                    if (dia == 1)
                                        signo = signo + "Perro/Zorro";
                                    else {
                                        if (dia >= 2 && dia <= 29)
                                            signo = signo + "Serpiente";
                                        else
                                            signo = signo + "Conejo/Ardilla";
                                    }
                                } else {
                                    if (mes == 7) {
                                        if (dia <= 27)
                                            signo = signo + "Tortuga";
                                        else
                                            signo = signo + "Murcielago";
                                    } else {
                                        if (mes == 8) {
                                            if (dia <= 22)
                                                signo = signo + "Murcielago";
                                            else
                                                signo = signo + "Escorpion";
                                        } else {
                                            if (mes == 10) {
                                                if (dia <= 17)
                                                    signo = signo + "Venado";
                                                else
                                                    signo = signo + "Buho/Lechuza";
                                            } else {
                                                if (mes == 12) {
                                                    if (dia <= 12)
                                                        signo = signo + "Pavo real";
                                                    else
                                                        signo = signo + "Lagarto";
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else
                        signo = "Fecha incorrecta";
                } else {
                    if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                        if (dia > 0 && dia < 31) {
                            if (mes == 4) {
                                if (dia <= 3)
                                    signo = signo + "Jaguar";
                                else
                                    signo = signo + "Perro/Zorro";
                            } else {
                                if (mes == 6) {
                                    if (dia <= 26)
                                        signo = signo + "Conejo/Ardilla";
                                    else
                                        signo = signo + "Tortuga";
                                } else {
                                    if (mes == 9) {
                                        if (dia <= 19)
                                            signo = signo + "Escorpion";
                                        else
                                            signo = signo + "Venado";
                                    } else {
                                        if (mes == 11) {
                                            if (dia <= 14)
                                                signo = signo + "Buho/Lechuza";
                                            else
                                                signo = signo + "Pavo real";
                                        }
                                    }
                                }
                            }
                        } else
                            signo = "Fecha incorrecta";
                    } else {
                        if (mes == 2) {
                            if (dia <= 6)
                                signo = signo + "Mono";
                            else
                                signo = signo + "Halcon";
                        }
                    }
                }
            } else
                signo = "Fecha incorrecta";
        }
        return signo;
    }

    private String prediccionSeleccionada() {
        String predicciones[] = {
                "Los astros te beneficiarán",
                "Nuestros padres mayas estarán siempre a tu lado",
                "Vivirás una buena y larga vida",
                "Deberías conectar más con tu familia",
                "Cosas buenas te aguardan a ti y a tus seres queridos",
                "Tendrás que tomar decisiones importantes, y no debes actuar con miedo o debilidad",
                "Mira hacia las estrellas, siempre observalas",
                "Si te esfuerzas conseguirás todo lo que te propones",
                "Ten cuidado, hay personas a las que no les caes bien",
                "¿Qué pasaría si esta noche te recuerdo lo que debes hacer?",
                "Algo peligroso está por llegar, preparate.",
                "Hoy será un mal dia, lo siento."};
        //Random ran = new Random();
        int i = (int) (Math.random() * 12) + 0;
        String prediccion = predicciones[i];
        return prediccion;
    }
}