package com.zatec.features.houses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.zatec.features.houses.databinding.FragmentDisplayHouseBinding
import com.zatec.features.houses.ui.PairListAdapter
import com.zatec.features.houses.ui.displaySeats
import com.zatec.features.houses.ui.displayWeapons
import com.zatec.features.houses.viewmodels.HousesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.internal.immutableListOf

/**
 * House fragment
 * Displays a house in detail
 * binding is the layout databinding file
 * viewmodel contains the house info
 * adapter handles the data to display in the recycler view
 */
@AndroidEntryPoint
class DisplayHouseFragment : Fragment() {

    lateinit var binding: FragmentDisplayHouseBinding

    private val viewmodel: HousesViewModel by viewModels()
    private val adapter = PairListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDisplayHouseBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        val id = arguments?.getString("id", "0")!!

        binding.infoList.adapter = adapter
        binding.infoList.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        viewLifecycleOwner.lifecycleScope.launch {
            viewmodel.flowHouse(id).collectLatest {
                it?.let { house ->
                    binding.item = house

                    val infoList = immutableListOf<Pair<String,String>>(
                        Pair(getString(R.string.coat_of_arms), house.coatOfArms.ifEmpty{getString(R.string.unknown)}),
                        Pair(getString(R.string.seats), house.displaySeats()?.ifEmpty{getString(R.string.unknown)}?:""),
                        Pair( getString(R.string.weapons), house.displayWeapons()?.ifEmpty{getString(R.string.unknown)}?:""),
                        Pair( getString(R.string.founded), house.founded.ifEmpty{getString(R.string.unknown)}),
                        Pair( getString(R.string.died), house.founder.ifEmpty{getString(R.string.unknown)})
                    )
                    adapter.submitList(infoList.toList())

                    //special add
                    with(house.name){
                        when{
                            contains("arryn", true)-> binding.icon.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.arryn, null))
                            contains("baelish", true)-> binding.icon.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.baelish, null))
                            contains("baratheon", true)-> binding.icon.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.baratheon, null))
                            contains("bolton", true)-> binding.icon.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.bolton, null))
                            contains("frey", true)-> binding.icon.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.frey, null))
                            contains("greyjoy", true)-> binding.icon.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.greyjoy, null))
                            contains("lannister", true)-> binding.icon.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.lannister, null))
                            contains("martell", true)-> binding.icon.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.martell, null))
                            contains("mormont", true)-> binding.icon.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.mormont, null))
                            contains("stark", true)-> binding.icon.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.stark, null))
                            contains("targaeryan", true)-> binding.icon.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.targaeryan, null))
                            contains("tully", true)-> binding.icon.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.tully, null))
                            contains("tarly", true)-> binding.icon.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.tarly, null))
                            contains("tyrell", true)-> binding.icon.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.tyrell, null))
                        }
                    }
                }
            }
        }

        viewmodel.getHouse(id)



        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}