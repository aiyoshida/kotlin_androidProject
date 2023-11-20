package hu.bme.aut.fpyyt9.shoppinglist
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Handlerを使って3秒後にShoppingListアクティビティに遷移する
        Handler().postDelayed({
            val intent = Intent(this, ShoppingList::class.java)
            startActivity(intent)
            finish() // SplashActivityを終了して、バックスタックから削除
        }, 3000) // 3000ミリ秒後に実行
    }
}
