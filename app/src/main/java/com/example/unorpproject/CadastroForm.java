package com.example.unorpproject;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.InputMismatchException;
import java.util.Objects;

public class CadastroForm extends AppCompatActivity {

    String[] mensagens = {"Cadastro bem sucedido!", "CPF Inválido!",
            "CPF já cadastrado!", "E-mail já cadastrado!", "Cadastro não realizado."};

    EditText et_nome, et_cpf, et_email, et_senha;
    Button bt_cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_form);
        Toolbar toolbar = findViewById(R.id.customtoolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        et_nome = findViewById(R.id.et_nome);
        et_cpf = findViewById(R.id.et_cpf);
        et_email = findViewById(R.id.et_email);
        et_senha = findViewById(R.id.et_senha);
        bt_cadastrar = findViewById(R.id.bt_cadastrar);

        //Colocando máscara de CPF
        mascara();

        bt_cadastrar.setOnClickListener(view -> {

            if(verificanome(et_nome) && verificacpf(et_cpf) &&
               verificaemail(et_email) && verificasenha(et_senha)) {

                geratoast(mensagens[0]);
                finish();
            }
        });
    }

    private void geratoast(String mensagens) {

        LayoutInflater inflater = getLayoutInflater();

        View layout = inflater.inflate(R.layout.toastf, findViewById(R.id.custom_toast_layout_id));

        TextView toastMessage = layout.findViewById(R.id.textdd);
        toastMessage.setText(mensagens);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    private boolean verificanome(EditText et_nome) {

        String colocounome = et_nome.getText().toString();

        if (colocounome.isEmpty()) {
            et_nome.setError("Campo vazio!");
            return (false);
        }
        return (true);
    }

    private boolean verificacpf(EditText cpf) {

        String sequenciacpf = cpf.getText().toString().replaceAll("[.]", "")
                .replaceAll("[-]", "");

        if (sequenciacpf.isEmpty()) {
            cpf.setError("Campo vazio!");
            return (false);
        }
        else if (!ValidaCPF.isCPF(sequenciacpf)) {
            geratoast(mensagens[1]);
            return (false);
        }
        else if (sequenciacpf.equals("45333868816")) {
            geratoast(mensagens[2]);
            return (false);
        }
        return (true);
    }

    private boolean verificaemail(EditText et_email) {

        String confereemail = et_email.getText().toString();

        if (confereemail.isEmpty()) {
            et_email.setError("Campo vazio!");
            return (false);
        }
        else if (confereemail.equals("ethan-cruise@hotmail.com")) {
            geratoast(mensagens[3]);
            return (false);
        }
        return (true);
    }

    private boolean verificasenha(EditText et_senha) {

        String temsenha = et_senha.getText().toString();

        if (temsenha.isEmpty()) {
            et_senha.setError("Campo vazio!");
            return (false);
        }
        return (true);
    }

    private void mascara() {
        et_cpf.addTextChangedListener(new TextWatcher() {

            boolean isUpdating;

            @Override
            public void beforeTextChanged (CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged (CharSequence s, int start, int before, int after) {

                // Quando o texto é alterado o onTextChange é chamado
                // Essa flag evita a chamada infinita desse método
                if (isUpdating) {
                    isUpdating = false;
                    return;
                }

                // Ao apagar o texto, a máscara é removida,
                // então o posicionamento do cursor precisa
                // saber se o texto atual tinha ou não, máscara
                boolean hasMask = s.toString().indexOf('.') > -1 || s.toString().indexOf('-') > -1;

                // Remove o '.' e '-' da String
                String str = s.toString().replaceAll("[.]", "")
                        .replaceAll("[-]", "");

                // As variáveis before e after dizem o tamanho
                // anterior e atual da String, se after > before
                // é pq está apagando
                if (after > before) {
                    // Se tem mais de 5 caracteres (sem máscara)
                    // coloca o '.' e o '-'
                    if (str.length() > 9) {
                        str = str.substring(0, 3) + '.' + str.substring(3, 6) + '.'
                                + str.substring(6, 9) + '-' + str.substring(9);
                    }
                    else if (str.length() > 6) {
                        str = str.substring(0, 3) + '.' + str.substring(3, 6) + '.'
                                + str.substring(6);
                    }
                    else if (str.length() > 3) {
                        str = str.substring(0, 3) + '.' + str.substring(3);
                    }
                    // Seta a flag pra evitar chamada infinita
                    isUpdating = true;
                    // Seta o novo texto
                    et_cpf.setText(str);
                    // Seta a posição do cursor
                    et_cpf.setSelection(et_cpf.getText().length());
                }
                else {
                    isUpdating = true;
                    et_cpf.setText(str);
                    // Se estiver apagando posiciona o cursor
                    // no local correto. Isso trata a deleção dos caracteres da máscara.
                    et_cpf.setSelection(Math.max(0, Math.min(hasMask ? start - before : start, str.length())));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public static class ValidaCPF {

        public static boolean isCPF(String CPF) {

            // Considera-se erro CPF's formados por uma sequência de números iguais
            if (CPF.equals("00000000000") || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) return (false);

            char dig10, dig11;
            int sm, i, r, num, peso;

            // "try" - protege o código para eventuais erros de conversão de tipo (int)
            try {
                // Cálculo do 1º dígito verificador
                sm = 0;
                peso = 10;
                for (i = 0; i < 9; i++) {
                    // Converte o i-ésimo caractere do CPF em um número:
                    // por exemplo, transforma o caractere '0' no inteiro 0
                    // (48 eh a posição de '0' na tabela ASCII)
                    num = (CPF.charAt(i) - 48);
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }

                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11)) {
                    dig10 = '0';
                }
                else {
                    dig10 = (char) (r + 48); // Converte no respectivo caractere numérico
                }

                // Cálculo do 2º dígito verificador
                sm = 0;
                peso = 11;
                for (i = 0; i < 10; i++) {
                    num = (CPF.charAt(i) - 48);
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }

                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11)) {
                    dig11 = '0';
                }
                else {
                    dig11 = (char) (r + 48);
                }

                // Verifica se os dígitos calculados conferem com os dígitos informados.
                return (dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10));
            } catch (InputMismatchException erro) {
                return (false);
            }
        }
    }
//
}
