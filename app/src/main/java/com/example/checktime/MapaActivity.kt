package com.example.checktime

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapaActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener{

    private lateinit var map:GoogleMap
    companion object{
        const val  REQUEST_CODE_LOCATION = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)
        createFragment()
    }

    private fun createFragment() {
        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarker()
        createPolyLines()
        createBus1()
        createBus3()
        createBus4()
        createBus5()
        createBus6()
        createBus7()
        enableLocation()
        createMarke()
        createBus()
        map.setOnMyLocationButtonClickListener(this)
        map.setOnMyLocationClickListener(this)
    }

    private fun createPolyLines(){
        val polylineOptions= PolylineOptions()
            .add(LatLng(19.616586326772293,-99.00444049339316))
            .add(LatLng( 19.61782937690137, -99.00372166137706))
            .add(LatLng( 19.618203300668895, -99.00433320503217))
            .add(LatLng( 19.619335172719616, -99.003764576721))
            .add(LatLng( 19.621199712423874,  -99.00339979629584))
            .add(LatLng(  19.620997595343496, -99.00449413757423))
            .add(LatLng(  19.621422040917466, -99.00483746032803))
            .add(LatLng(  19.62862733876976, -99.0021767089856))
            .add(LatLng(  19.63460961262723, -99.00191921692024))
            .add(LatLng(  19.639035497625912, -99.00290621943583))
            .add(LatLng(  19.64170312302973, -99.00372161097636))
            .add(LatLng(  19.64703823950289, -99.00509490067446))
            .add(LatLng(  19.652454011041556, -99.00505198532997))
            .add(LatLng(  19.665143984142674, -99.00423660980032))
            .add(LatLng(  19.670397515656262, -99.0038503721786))
            .add(LatLng(  19.674236514813686, -99.00226250444203))
            .add(LatLng(  19.676903554824733, -98.99998799119734))
            .add(LatLng(  19.67819664915305, -98.99912968431288))
            .add(LatLng(  19.67981300238786, -98.99672642503522))

            .add(LatLng(  19.685833770426996, -98.99041788380951))
            .add(LatLng(  19.691086608323573, -98.98612634938601))
            .add(LatLng(  19.695248355428134, -98.98269310561918))
            .add(LatLng(   19.705874447182694, -98.97762909499912))
            .add(LatLng(   19.71497349145966, -98.97299421940279))
            .add(LatLng(   19.715660285743596, -98.97282255802588))
            .add(LatLng(   19.71335749327409, -98.96904600773308))
            .add(LatLng(   19.712387886522507, -98.96771563206131))


            //.add(LatLng(40.419173113350965,-3.705976009368897))

            .width(15f)
            .color(ContextCompat.getColor(this, R.color.kotlin))

        val polyline: Polyline = map.addPolyline(polylineOptions)
        val pattern : List<PatternItem> = listOf(
            Dot(), Gap(10f), Dash(50f), Gap(10f)
        )
        polyline.pattern = pattern
        //polyline.startCap = RoundCap()
        //polyline.endCap = CustomCap(BitmapDescriptorFactory.fromResource(R.drawable.))
    }

    private fun createMarker(){

        val coordinates = LatLng(19.712387886522507, -98.96771563206131)
        val marker = MarkerOptions()
            .position(coordinates)
            .title("Tecamac")
            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_mapa_foreground))


        map.addMarker(marker)


        /** map.animateCamera(
        CameraUpdateFactory.newLatLngZoom(coordinates, 18f),
        4000,
        null
        )**/
    }

    private fun createMarke(){

        val coordinates = LatLng(19.616586326772293, -99.00444049339316)
        val marker = MarkerOptions()
            .position(coordinates)
            .title("Central de Abastos")
            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_mapa_foreground))

        map.addMarker(marker)

    }

    private fun createBus(){

        val coordinates = LatLng(19.621199712423874, -99.00339979629584)
        val marker = MarkerOptions()
            .position(coordinates)
            .title("Unidad 1")
            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_foreground))

        map.addMarker(marker)

    }
    private fun createBus1(){

        val coordinates = LatLng(19.62862733876976, -99.0021767089856)
        val marker = MarkerOptions()
            .position(coordinates)
            .title("Unidad 2")
            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_foreground))

        map.addMarker(marker)

    }
    private fun createBus3(){

        val coordinates = LatLng(19.64703823950289, -99.00509490067446)
        val marker = MarkerOptions()
            .position(coordinates)
            .title("Unidad 2")
            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_foreground))

        map.addMarker(marker)

    }
    private fun createBus4(){

        val coordinates = LatLng(19.670397515656262, -99.0038503721786)
        val marker = MarkerOptions()
            .position(coordinates)
            .title("Unidad 2")
            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_foreground))

        map.addMarker(marker)

    }

    private fun createBus5(){

        val coordinates = LatLng(19.685833770426996, -98.99041788380951)
        val marker = MarkerOptions()
            .position(coordinates)
            .title("Unidad 2")
            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_foreground))

        map.addMarker(marker)

    }
    private fun createBus6(){

        val coordinates = LatLng(19.695248355428134, -98.98269310561918)
        val marker = MarkerOptions()
            .position(coordinates)
            .title("Unidad 2")
            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_foreground))

        map.addMarker(marker)

    }
    private fun createBus7(){

        val coordinates = LatLng(19.674236514813686, -99.00226250444203)
        val marker = MarkerOptions()
            .position(coordinates)
            .title("Unidad 2")
            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_foreground))

        map.addMarker(marker)

    }

    private fun isLocationPermissionGranted()= ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED


    private fun enableLocation(){
        if(!::map.isInitialized) return
        if(isLocationPermissionGranted()){
            map.isMyLocationEnabled = true
        }else{
            requestLocationPermission()
        }
    }
    private fun requestLocationPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(this,"Acepta los permisos", Toast.LENGTH_SHORT).show()
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION )
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            REQUEST_CODE_LOCATION -> if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                map.isMyLocationEnabled = false
            }else{
                Toast.makeText(this,"Acepta los permisos", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if(!::map.isInitialized) return
        if(!isLocationPermissionGranted()){
            map.isMyLocationEnabled = false
            Toast.makeText(this,"Acepta los permisos", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this,"Boton Pulsado", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(this,"Estas en ${p0.latitude}, ${p0.longitude}", Toast.LENGTH_SHORT).show()
    }

}