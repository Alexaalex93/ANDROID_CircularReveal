package com.example.cice.circularreveal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Trace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Transition.TransitionListener transitionListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button lanzarBtn = (Button) findViewById(R.id.buttonMostrar);
        lanzarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Activar animacion
                mostrarAnim();
            }
        });

        Button esconderBtn = (Button) findViewById(R.id.buttonEsconder);
        esconderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ocultarAnim();

            }
        });
        transitionListener = new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {

            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        };
        getWindow().getEnterTransition().addListener(transitionListener); //Para poder modificar el listener
    }


    //Mostrar animacion
    void mostrarAnim(){
        View imagenV = findViewById(R.id.imageView);

        //Sacar el centro de la imagen
        int cx = imagenV.getMeasuredWidth() / 2;
        int cy = imagenV.getMeasuredHeight() / 2;

        //Sacar el radio de la imagen
        int radio = Math.max(imagenV.getWidth(), imagenV.getHeight()) / 2;

        //Creamos el animator
        Animator anim = ViewAnimationUtils.createCircularReveal(imagenV, cx, cy, 0, radio);

        //Velocidad
        anim.setDuration(3000);

        //Mostrar la vista
        imagenV.setVisibility(View.VISIBLE);
        anim.start();
    }

    //Ocultar animacion
    void ocultarAnim(){
        final View imagenV = findViewById(R.id.imageView);

        //Sacar el centro de la imagen
        int cx = imagenV.getMeasuredWidth() / 2;
        int cy = imagenV.getMeasuredHeight() / 2;

        //Sacar el radio de la imagen
        int radio = Math.max(imagenV.getWidth(), imagenV.getHeight()) / 2;

        //Creamos el animator
        Animator anim = ViewAnimationUtils.createCircularReveal(imagenV, cx, cy, radio, 0);

        //Velocidad
        anim.setDuration(3000);

        //Ocultar la vista la vista
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                imagenV.setVisibility(View.INVISIBLE);
            }
        });
        //imagenV.setVisibility(View.INVISIBLE);
        anim.start();
    }

}
