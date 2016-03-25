    public void tunnelEffect(Player player) {
        Location eyeLocation = player.getEyeLocation();

        double eyeX = eyeLocation.getX();
        double eyeY = eyeLocation.getY();
        double eyeZ = eyeLocation.getZ();
        double eyeYaw = Math.toRadians(eyeLocation.getYaw() + 90.0F);
        double eyePitch = Math.toRadians(eyeLocation.getPitch() + 90.0F);

        // Note, I swapped the Y and Z variables. This is different from
        // the tutorial in a small way. The core is pretty much the same.
     
        double x = Math.sin(eyeYaw) * Math.cos(eyePitch);
        double z = Math.sin(eyeYaw) * Math.sin(eyePitch);
        double y = Math.cos(eyeYaw);

        for (int i = 1; i <= 50; i++) {
            Location loc = new Location(player.getWorld(), eyeX + i * x, eyeY + i * y, eyeZ + i * z);
            // Send effect
        }
    }
