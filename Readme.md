
add 'ngame' in settings.gradle

include 'desktop', 'android', 'ios', 'html', 'core', 'ngame'


in build.gradle add:

project(":ngame") {
    apply plugin: "java"


    dependencies {
        compile "com.badlogicgames.gdx:gdx:$gdxVersion"
        compile "com.badlogicgames.gdx:gdx-controllers:$gdxVersion"
		compile fileTree(dir: 'libs', include: '*.jar')
    }
}

in build.gradle core dependencies add:
	compile project(":ngame")