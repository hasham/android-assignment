package ui.main

import android.R
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.hasht.translationapp.databinding.ActivityMainBinding
import common.Status
import dagger.hilt.android.AndroidEntryPoint
import data.model.Language


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ArrayAdapter<Language>
    private var languages = arrayListOf<Language>()
    private var selectedLanguage: Language? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUI()
        setUpObservers()
    }


    private fun setUpUI() {
        adapter =
            ArrayAdapter(
                this@MainActivity,
                R.layout.simple_list_item_1,
                languages
            )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.dropDownLanguagesSpinner.adapter = adapter
        binding.buttonTranslate.setOnClickListener {
            val text = binding.editTextInput.text.toString()
            val selectedLanguage: Language =
                binding.dropDownLanguagesSpinner.selectedItem as Language
            if (text.isNotBlank()) {
                getTranslation(text, selectedLanguage.code)
            }
        }

    }

    private fun setUpObservers() {
        viewModel.getLanguages().observe(this@MainActivity, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.textViewResult.visibility = View.VISIBLE
                        binding.loading.visibility = View.GONE
                        resource.data?.let { languages -> retrieveLanguageList(languages) }
                    }
                    Status.ERROR -> {
                        binding.textViewResult.visibility = View.VISIBLE
                        binding.loading.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.loading.visibility = View.VISIBLE
                        binding.textViewResult.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveLanguageList(newLanguages: List<Language>) {

        languages.clear()
        languages.addAll(newLanguages)
        adapter.notifyDataSetChanged()
        binding.dropDownLanguagesSpinner.invalidate()
        binding.dropDownLanguagesSpinner.setSelection(0)
    }

    private fun getTranslation(text: String, target: String) {
        viewModel.getTranslation(text, target).observe(this@MainActivity, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.textViewResult.visibility = View.VISIBLE
                        binding.loading.visibility = View.GONE
                        resource.data?.let { translation ->
                            binding.textViewResult.text = translation.translatedText
                        }
                    }
                    Status.ERROR -> {
                        binding.textViewResult.visibility = View.VISIBLE
                        binding.loading.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.loading.visibility = View.VISIBLE
                        binding.textViewResult.visibility = View.GONE
                    }
                }
            }
        })

    }
}