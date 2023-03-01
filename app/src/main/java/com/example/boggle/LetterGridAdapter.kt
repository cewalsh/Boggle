import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.boggle.databinding.GridItemLetterBinding

private const val TAG = "LetterGridAdapter"

class LetterGridHolder(val binding: GridItemLetterBinding) : RecyclerView.ViewHolder(binding.root) {

}

class LetterGridAdapter(private val letters: List<Char>): RecyclerView.Adapter<LetterGridHolder>(){
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
        }
    }

}