import android.accessibilityservice.AccessibilityButtonController.AccessibilityButtonCallback
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.boggle.SharedViewModel
import com.example.boggle.databinding.GridItemLetterBinding

private const val TAG = "LetterGridAdapter"

class LetterGridHolder(val binding: GridItemLetterBinding) : RecyclerView.ViewHolder(binding.root) {

}

class LetterGridAdapter(private val letters: List<Char>, private val sharedViewModel: SharedViewModel): RecyclerView.Adapter<LetterGridHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterGridHolder {
        Log.d(TAG,"This is called")
        val inflater = LayoutInflater.from(parent.context)
        val binding = GridItemLetterBinding.inflate(inflater, parent, false)
        return LetterGridHolder(binding)

    }

    override fun getItemCount() = letters.count()

    override fun onBindViewHolder(holder: LetterGridHolder, position: Int) {
        val letter = letters[position]

        Log.d(TAG, "adding letter " + letter)

        holder.apply{
            binding.letter.text = letter + ""
            binding.letter.setOnClickListener {
                Log.d(TAG, "pressed " + letter)
                if(sharedViewModel.updateLetter(letter, position) == -1){
                    Toast.makeText(
                        binding.root.context,
                        "Not a valid selection!",
                        Toast.LENGTH_SHORT
                    ).show()
                }else{
                    binding.letter.isClickable = false
                    binding.letter.alpha = 0.5f
//                    binding.letter.background.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(Color.GRAY, BlendModeCompat.MULTIPLY)
                }
            }
        }
    }

}