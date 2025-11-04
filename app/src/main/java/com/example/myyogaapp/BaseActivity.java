package com.example.myyogaapp;



import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * BaseActivity proporciona el método showSimulatedActionSuccess
 * para manejar el requisito de mostrar un AlertDialog después de cada acción principal.
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * Muestra un AlertDialog simulando una acción exitosa (Login, Registro, Recuperación).
     * @param messageId ID del recurso string que contiene el mensaje a mostrar.
     * @param callback Opcional: acción a ejecutar después de que el usuario presione "Aceptar".
     */
    protected void showSimulatedActionSuccess(int messageId, final Runnable callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.dialog_success_title))
                .setMessage(getString(messageId))
                .setPositiveButton(getString(R.string.dialog_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (callback != null) {
                            callback.run();
                        }
                    }
                })
                .setCancelable(false)
                .show();
    }
}

